package com.family.system.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.family.configuration.GlobayConst;
import com.family.system.entity.User;
import com.family.system.entity.DO.UserAD;
import com.family.system.entity.VO.UserVO;
import com.family.system.mapper.UserMapper;
import com.family.system.service.IUserService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.PassCodeChange;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.family.utils.VerifyCode;
import com.github.benmanes.caffeine.cache.Cache;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 一些获取个人信息的接口
 * @author tWX932595
 *
 */

@RestController
@RequestMapping("personal/v1.0")
public class PersonalCotroller {

	@Autowired
	private IUserService iUserService;
	@Autowired
	private UserMapper iUserMapper;
	@Resource(name="verifyCode")
    Cache<String, String> verifyCache;

	Logger logger = LoggerFactory.getLogger(PersonalCotroller.class);
	@Value("${uploadFile.hostPath}")
    private String hostPath;
    @Value("${uploadFile.webImgPath}")
    private String webImgPath;
    @RequestMapping(value = "uploadHead", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject ossFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultUtil.error("文件是空的");
            }

            System.out.println("服务器文件地址:"+hostPath);
            String hz= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = System.currentTimeMillis() + UUID.randomUUID().toString()+hz;
            FileUtil.writeBytes(file.getBytes(), hostPath + fileName);
            System.out.println("文件"+hostPath + fileName);
            return ResultUtil.successData(webImgPath.replaceAll("\\*\\*",fileName));
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("上传文件失败！");
            return ResultUtil.error("上传文件失败");
        }
    }
	/**
	 * 依据ID获取系统用户表详情 Auther:feng
	 */
	@PostMapping("getUserById")
	@ApiOperation("依据ID获取系统用户表详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "系统用户表的id", required = false, example = "1") })
	public ResultObject getUserSingleById(@RequestHeader("userId")Integer userId) {
		try {
			UserVO entity = new UserVO();
			entity = iUserService.getSingleInfoById(userId);
			return ResultUtil.successData(entity);
		} catch (Exception e) {
			logger.error(e + "依据ID获取系统用户表详情异常");
			return ResultUtil.error("异常");
		}
	}
	/**
	 * 依据ID获取系统用户表详情 Auther:feng
	 */
	@PostMapping("saveUserInfo")
	@ApiOperation("获取当前登录用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "系统用户表的id", required = false, example = "1") })
	public ResultObject saveUserInfo(UserAD user,@RequestHeader("userId")Integer userId) {
		try {
			user.setUserId(userId);
			user.setUserPwd(null);
			iUserService.modUser(EntityChangeRquestView.createDOToEntity(user, new User()));
			return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "依据ID获取系统用户表详情异常");
			return ResultUtil.error("异常");
		}
	}
	/**
	 * 依据ID获取系统用户表详情 Auther:feng
	 */
	@PostMapping("updatePassword")
	@ApiOperation("用户修改密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "系统用户表的id", required = false, example = "1") })
	public ResultObject updatePassword(String oldPass,String newPass,@RequestHeader("userId")Integer userId) {
		try {
			User old=iUserMapper.selectByPrimaryKey(userId);
			if(old==null) {
				return ResultUtil.error("非法操作");
			}
			if(!old.getUserPwd().equals(PassCodeChange.encode(oldPass))) {
				return ResultUtil.error("旧密码错误");
			}

			User user=new User();
			user.setUserId(userId);
			user.setUserPwd(PassCodeChange.encode(newPass));
			iUserMapper.updateByPrimaryKeySelective(user);
			return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "依据ID获取系统用户表详情异常");
			return ResultUtil.error("重置密码失败，请检查新旧密码是否合法");
		}
	}
	/**
	 * 依据ID获取系统用户表详情 Auther:feng
	 */
	@GetMapping("getImageVerifyCode")
	@ApiOperation("生成图片验证码")
	public void getImageVerifyCode(HttpServletRequest request,HttpServletResponse response,String vtime) {
		try {
			if(StringUtils.isNull(vtime)) {
				return;
			}
			response.addHeader("Content-Type", "image/gif;charset=utf-8");
			VerifyCode verifyCode = new VerifyCode();
			BufferedImage bi = verifyCode.getImage(); // 生成图片
			GlobayConst.VerifyCode.put("", verifyCode.getText());
			logger.debug("验证码："+verifyCode.getText());
			verifyCache.put(GlobayConst.LoginVerifyCodeKey+vtime, verifyCode.getText());
	        request.getSession().setAttribute("session_verifyCode", verifyCode.getText()); // 保存图片上的文本到session域中
	        VerifyCode.output(bi, response.getOutputStream());// 把图片响应给客户端
		} catch (Exception e) {
			logger.error(e + "依据ID获取系统用户表详情异常");
		}
	}
}
