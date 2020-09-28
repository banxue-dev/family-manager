package com.family.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.configuration.GlobayConst;
import com.family.system.entity.User;
import com.family.system.entity.VO.LoginVO;
import com.family.system.entity.VO.UserVO;
import com.family.system.mapper.MenuMapper;
import com.family.system.service.IUserService;
import com.family.utils.JwtUtil;
import com.family.utils.OrgCodeGreater;
import com.family.utils.PassCodeChange;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.github.benmanes.caffeine.cache.Cache;

import io.swagger.annotations.ApiOperation;

@RequestMapping("login")
@RestController
public class LoginController {

	@Autowired
	private IUserService userService;
	@Autowired
	private MenuMapper iMenuMapper;
	@Resource(name="authCache")
    Cache<String, String> authCache;

	@Resource(name="verifyCode")
    Cache<String, String> verifyCache;
	@RequestMapping("")
	@ApiOperation("登录")
	public ResultObject login(String username,String password,String vercode,String vtime,HttpServletResponse response,HttpServletRequest request) {
		try {
			if(StringUtils.isNullStrings(username,password,vercode)) {

				return ResultUtil.error("参数不完整");
			}
			String localVerifyCode=(String) verifyCache.asMap().get(GlobayConst.LoginVerifyCodeKey+vtime);
			if(StringUtils.isNull(localVerifyCode)) {
				return ResultUtil.error("验证码已过期");
			}
			if(!localVerifyCode.toLowerCase().contains(vercode.toLowerCase())) {
				return ResultUtil.error("验证码错误");
			}
			User u=new User();
			u.setUserName(username);
			u.setUserPwd(PassCodeChange.encode(password));
			UserVO v=userService.login(u);
			if(v==null) {
				return ResultUtil.error("用户名或密码错误");
			}
			if(StringUtils.isNull(v.getOrgCode())) {
				return ResultUtil.error("该用户还未分配组织");
			}
			LoginVO lv=new LoginVO();
	        String token=JwtUtil.createJWT(username,"subject",12*60*60*1000);
	        lv.setToken(token);
	        lv.setOrgCode(OrgCodeGreater.encode(v.getOrgCode()));
	        lv.setUserId(v.getUserId());
	        lv.setUserAuth(iMenuMapper.getUserHaveAuth(v.getUserId()));
	        //response.setHeader("token", token);
			return ResultUtil.successData(lv);
		}catch(Exception e) {
			return ResultUtil.error("登录失败"+e);
		}
	}
}
