package com.javsrc.web.controller.administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.LogFactory;

import com.javsrc.entity.Product;
import com.javsrc.service.ProductService;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/administrator/alterOne")
public class AlterOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Integer pid=Integer.parseInt(request.getParameter("id"));
			Product product = new Product();
			product.setId(pid);
			Map<String,Object> map = new HashMap<String, Object>();
			DiskFileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			//提交组件的列表
			List<FileItem> list=upload.parseRequest(request);
			
			for(FileItem fi:list) {
				String fieldName=fi.getFieldName();
				
				if(fi.isFormField()) {
					String stringValues = fi.getString("utf-8");
					map.put(fieldName, stringValues);
				}else {
					String fileName=fi.getName();
					if(fileName!="") {
						String imageName = fileName.substring(fileName.lastIndexOf("\\")+1);
						InputStream is=fi.getInputStream();
						String productPath=getServletContext().getRealPath("/img");
						FileOutputStream os=new FileOutputStream(new File(productPath,imageName));
						IOUtils.copy(is, os);
						os.close();
						is.close();
						fi.delete();
						String path=productPath+"/"+imageName;
						map.put("thumbnail", path);
					}
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			BeanUtils.populate(product,map);
			Integer sales_volume=Integer.parseInt(request.getParameter("sales_volume"));
			System.out.println(sales_volume);
			product.setSales_volume(sales_volume);
			product.setCreate_time(format.parse(request.getParameter("create_time").replace(".0","")));
			product.setThumbnail(request.getParameter("thumbnail"));
			ProductService service=new ProductService();
			service.update(product);
			request.getRequestDispatcher("/administrator/product/alter.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("修改商品失败！");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
