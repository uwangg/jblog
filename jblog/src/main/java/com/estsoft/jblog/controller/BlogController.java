package com.estsoft.jblog.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Controller
@RequestMapping("/blog/{user_id}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	private static final String SAVE_PATH = "/temp";

	@RequestMapping("")
	public String index(
			@PathVariable String user_id,
			@RequestParam(value="category_no", required=true, defaultValue="") Long category_no,
			@RequestParam(value="post_no", required=true, defaultValue="") Long post_no,
			Model model) {
		BlogVo blogVo = blogService.get(user_id);	// 접속한 블로그 정보 가져옴
		
		List<CategoryVo> categoryList = categoryService.getList(blogVo.getNo());
		List<PostVo> postList = null;
		PostVo postVo = null;
		
		if(category_no == null && post_no == null) {	// 블로그 처음 접속시 메인화면
			category_no = categoryList.get(0).getNo();
			postList = postService.getList(category_no);
			if(postList.size() == 0) {	// 카테고리 내에 포스트가 0개인 경우 (ex. 회원가입을 막했을때)
				postVo = null;
			} else {	// 카테고리 내에 포스트가 1개라도 존재할 경우
				postVo = postList.get(0); 	// 포스트중 가장 첫번째 포스트를 보여줌
			}
		} else if(category_no != null && post_no == null) {	// 카테고리만 선택했을 경우
			postList = postService.getList(category_no);
			if(postList.size() == 0) {	// post가 0개인 경우
				postVo = null;
			} else {
				postVo = postList.get(0); 	// 포스트중 가장 첫번째 포스트를 보여줌
			}
		} else {	// 카테고리 or 포스트 선택시
			postList = postService.getList(category_no);
			if(postList.size() == 0) {	// post가 0개인 경우
				postVo = null;
			} else {
				postVo = postService.get(post_no);
			}
		}
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		model.addAttribute("category_no", category_no);
		return "blog/blog-main";
	}

	@RequestMapping("/admin")
	public String adminMain(){
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(
			@ModelAttribute BlogVo vo,
			@RequestParam( "imgfile" ) MultipartFile file,
			Model model
			) {
		
		BlogVo blogVo = blogService.get(vo.getUser_id());
		System.out.println("vo = " + vo);
		if(vo.getTitle() != null) {	// 바꿀값이 있다면
			blogVo.setTitle(vo.getTitle());
		}
		if( file.isEmpty() == false ) {
			
	        String fileOriginalName = file.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
	        String saveFileName = genSaveFileName( extName );
	        
	        writeFile( file, SAVE_PATH, saveFileName );
	       
	        String url = "/jblog/product-images/" + saveFileName;
	        blogVo.setImg(url);
	        model.addAttribute( "productImageUrl1", url );      
		}

		blogService.update(blogVo);
		
		return "redirect:/blog/{user_id}/admin";
	}

	// 카테고리 폼
	@RequestMapping("/admin/category")
	public String adminCategory() {
		return "blog/blog-admin-category";
	}
	
	// 카테고리
	@RequestMapping("/admin/category-list")
	@ResponseBody
	public Map<String, Object> ajaxCategoryList(HttpServletRequest request) {
		BlogVo blogVo = (BlogVo)request.getAttribute("blogVo");
		List<CategoryVo> list = categoryService.getList(blogVo.getNo());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);

		return map;
	}
	
	// 카테고리 추가
	@RequestMapping("/admin/category-insert")
	@ResponseBody
	public Map<String, Object> ajaxCategoryInsert(@ModelAttribute CategoryVo vo) {
		
		Long no = categoryService.insert(vo);
		vo.setPosting(0L);
		vo.setNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo);

		return map;
	}
	
	@RequestMapping("/admin/category-delete")
	@ResponseBody
	public Map<String, Object> ajaxCategoryDelete(@ModelAttribute CategoryVo vo) {
		categoryService.delete(vo.getNo());
		postService.delete(vo.getNo());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		return map;
	}

	
	// 글작성 폼
	@RequestMapping("/admin/writeform")
	public String adminWriteform(@PathVariable String user_id, Model model) {
		BlogVo blogVo = blogService.get(user_id);
		List<CategoryVo> categoryList = categoryService.getList(blogVo.getNo());
		model.addAttribute("categoryList", categoryList);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(
			@PathVariable String user_id,
			@Valid @ModelAttribute PostVo postVo, 
			BindingResult result, 
			Model model) {
		BlogVo blogVo = blogService.get(user_id);
		List<CategoryVo> categoryList = categoryService.getList(blogVo.getNo());
		model.addAttribute("categoryList", categoryList);
		
		if ( result.hasErrors() ) {	// 아이디, 이름, 패스워드부분이 빈칸인지 아닌지 검사
			model.addAttribute(result.getModel());
			model.addAttribute("postVo", postVo);
			return "blog/blog-admin-write";
		}
		
		postService.insert(postVo);
		
		CategoryVo categoryVo = categoryService.get(postVo.getCategory_no());	
		categoryService.update(categoryVo);
		
		return "redirect:/blog/{user_id}/admin/writeform";
	}
	
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + "/" + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}

//	private String checkUrl(Model model, String url) {
//		BlogVo vo = blogService.get(user_id());
//		if (vo == null) { // user_id가 존재하지 않을 때
//			return "redirect:/main";
//		}
//		model.addAttribute("vo", vo);
//		return url;
//	}
//	private String user_id() {
//		Map<String, String> variables = (Map<String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		return variables.get("user_id");
//	}
}
