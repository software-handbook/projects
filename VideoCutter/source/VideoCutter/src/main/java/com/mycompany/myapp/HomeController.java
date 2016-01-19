package com.mycompany.myapp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.text.TableView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("model", new VideoUploadModel());
		return "home";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadHandler(@ModelAttribute("model") VideoUploadModel videoModel, BindingResult br){
		ModelAndView mv = new ModelAndView("uploadvideo");
		logger.debug("Processing uploading...");
		
		if(br.hasErrors()){
			logger.error("Binding errors: " + br.getErrorCount());
			for (ObjectError objError : br.getAllErrors()){
				logger.error("Error message:" + objError.getDefaultMessage());
				}
			mv.addObject("nError", br.getErrorCount());
		} else {
			logger.debug("Video title: " + videoModel.getVideoTitle());
			logger.debug("Video Size: " + videoModel.getVideoFile().getSize());
			mv.addObject("videoTitle", videoModel.getVideoTitle());
			mv.addObject("videoSize", videoModel.getVideoFile().getSize());
			mv.addObject("originalFileName",videoModel.getVideoFile().getOriginalFilename());
		}
		
		return mv;
		
		
		
	}
}

/*public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		return "uploadvideo";
	}

	private static final String VIDEOS = "videos";
	private static final String TOMCAT_HOME_PROPERTY = "catalina.home";
    private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
    private static final String VIDEOS_PATH = TOMCAT_HOME_PATH + File.separator + VIDEOS;

    private static final File 	VIDEOS_DIR = new File(VIDEOS_PATH);
    private static final String VIDEOS_DIR_ABSOLUTE_PATH = VIDEOS_DIR.getAbsolutePath() + File.separator;
    private static final String FAILED_UPLOAD_MESSAGE = "You failed to upload [%s] because the file because %s";
    private static final String SUCCESS_UPLOAD_MESSAGE = "You successfully uploaded file = [%s]";

    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public String uploadFileHandler(@RequestParam("name") String name,
    public String uploadFileHandler (@RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("uploadvideo");
        String name = "title";
            logger.debug("name = " + name);
        	createDirIfNeeded();
            mv.addObject("message", createFile(name, file));

            mv.addObject("path", VIDEOS_DIR_ABSOLUTE_PATH + name +".mov");
            mv.addObject("fileName", name);
            mv.addObject("fileSize", file.getSize());
           return "uploadvideo";
    }
    

    private String createFile(String name, MultipartFile file) {
    	try{
    	File video = new File(VIDEOS_DIR_ABSOLUTE_PATH + name);
    	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(video));
    	stream.write(file.getBytes());
    	stream.close();
		return String.format(SUCCESS_UPLOAD_MESSAGE, name);
    } catch (Exception e){
    	return String.format(FAILED_UPLOAD_MESSAGE,  name, e.getMessage());
    }
	}

	private void createDirIfNeeded() {
        if (!VIDEOS_DIR.exists()) {
        	VIDEOS_DIR.mkdirs();
        }
    }
}
	*/
