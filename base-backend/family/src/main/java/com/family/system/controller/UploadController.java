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
import com.family.normal.entity.FileSystem;
import com.family.normal.entity.ImgManager;
import com.family.normal.service.IFileSystemService;
import com.family.normal.service.IImgManagerService;
import com.family.system.entity.User;
import com.family.system.entity.DO.UserAD;
import com.family.system.entity.VO.UserVO;
import com.family.system.mapper.UserMapper;
import com.family.system.service.IUserService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.ImageCompress;
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
 * 上传的接口
 * @author tWX932595
 *
 */

@RestController
@RequestMapping("upload/v1.0")
public class UploadController {

	@Autowired
	private IImgManagerService imgManagerService;
	@Autowired
	private IFileSystemService fileSystemService;

	Logger logger = LoggerFactory.getLogger(UploadController.class);
	@Value("${uploadFile.hostPath}")
    private String hostPath;
    @Value("${uploadFile.webImgPath}")
    private String webImgPath;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    //限制的文件类型
    private static String limitImg=".png.jpg.jpeg.gif.bmp";
    private static String limitFile=".doc.xls.xlsx.txt.zip.ppt.pdf.docx";
    /**
     	*  单纯上传图片，会返回图片路径
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadHead", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject ossFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        try {
            if (file.isEmpty()) {
                return ResultUtil.error("文件是空的");
            }
            String imgdir="userHeadImg/";
            String hz= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(limitImg.indexOf(hz.toLowerCase())<0) {
            	return ResultUtil.error("文件异常，只能上传"+limitImg+"的格式文件");
            }
            String fileName = System.currentTimeMillis() + UUID.randomUUID().toString()+hz;
           // String compressName=hostPath+"compress"+fileName;
            FileUtil.writeBytes(file.getBytes(), hostPath +imgdir+ fileName);
            //ImageCompress.reduceImg(file.getInputStream(), compressName, hz, 120, 60, 0.2f);
            String url=webImgPath.replaceAll("\\*\\*",imgdir+ fileName);
			/*
			 * String uh=request.getScheme()+"://"+request.getServerName()+":"+request.
			 * getServerPort()+contextPath; ImgManager imgManager=new ImgManager();
			 * imgManager.setLink(uh+'/'+url);
			 * imgManager.setFileName(file.getOriginalFilename());
			 * imgManagerService.addNewImgManager(imgManager);
			 */
            return ResultUtil.successData(url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("上传文件失败！");
            return ResultUtil.error("上传文件失败");
        }
    }
    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject uploadImg(@RequestParam("file") MultipartFile file,HttpServletRequest request,String sfileName
) {
        try {
            if (file.isEmpty()) {
                return ResultUtil.error("文件是空的");
            }
            String imgdir="userHeadImg/";
            String hz= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(limitImg.indexOf(hz.toLowerCase())<0) {
            	return ResultUtil.error("文件异常，只能上传"+limitImg+"的格式文件");
            }
            String fileName = System.currentTimeMillis() + UUID.randomUUID().toString()+hz;
            String compressName="compress"+fileName;
            FileUtil.writeBytes(file.getBytes(), hostPath +imgdir+ fileName);
            ImageCompress.WriteComparessImg(hostPath+compressName, file, 0.9f);
            String url=webImgPath.replaceAll("\\*\\*",imgdir+ fileName);
            String uh=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
            ImgManager im=new ImgManager();
            im.setFileName(sfileName);
            im.setLink(uh+url);
            im.setThumImgPath(imgdir+compressName);
            im.setImgPath(imgdir+fileName);
            im.setThumbnailLink(uh+webImgPath.replaceAll("\\*\\*",imgdir+ compressName));
            ResultObject ro=imgManagerService.addNewImgManager(im);
            if(!ro.isSuccess()) {
            	return ResultUtil.error("添加失败");
            }
            return ResultUtil.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("上传文件失败！");
            return ResultUtil.error("上传文件失败");
        }
    }
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request,String sfileName
    		) {
    	try {
    		if (file.isEmpty()) {
    			return ResultUtil.error("文件是空的");
    		}
    		String filedir="file/";
    		String hz= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    		if(limitFile.indexOf(hz.toLowerCase())<0) {
    			return ResultUtil.error("文件异常，只能上传"+limitFile+"的格式文件");
    		}
    		String fileName = System.currentTimeMillis() + UUID.randomUUID().toString()+hz;
    		FileUtil.writeBytes(file.getBytes(), hostPath +filedir+ fileName);
    		String url=webImgPath.replaceAll("\\*\\*",filedir+ fileName);
    		String uh=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
    		FileSystem im=new FileSystem();
    		im.setFileName(sfileName);
    		im.setFileUrl(uh+url);
    		im.setFilePath(filedir+fileName);
    		ResultObject ro=fileSystemService.addNewFileSystem(im);
    		if(!ro.isSuccess()) {
    			return ResultUtil.error("添加失败");
    		}
    		return ResultUtil.success();
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.warn("上传文件失败！");
    		return ResultUtil.error("上传文件失败");
    	}
    }
}
