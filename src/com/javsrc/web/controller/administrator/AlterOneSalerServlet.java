package com.javsrc.web.controller.administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.javsrc.entity.Product;
import com.javsrc.entity.Saler;
import com.javsrc.service.ProductService;
import com.javsrc.service.SalerService;

/**
 * Servlet implementation class AlterOneSalerServlet
 */
@WebServlet("/AlterOneSalerServlet")
public class AlterOneSalerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterOneSalerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer pid=Integer.parseInt(request.getParameter("id"));
			Saler product = new Saler();
			product.setId(pid);
			Map<String,Object> map = new HashMap<String, Object>();
			DiskFileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			//提交组件的列表
			List<FileItem> list=upload.parseRequest(request);
			
			for(FileItem fi:list) {
				String fieldName=fi.getFieldName();
				String stringValues = fi.getString("utf-8");
				map.put(fieldName, stringValues);
				
				/*
				 * if(fi.isFormField()) { String stringValues = fi.getString("utf-8");
				 * map.put(fieldName, stringValues); }else { String fileName=fi.getName();
				 * if(fileName!="") { String imageName =
				 * fileName.substring(fileName.lastIndexOf("\\")+1); InputStream
				 * is=fi.getInputStream(); String
				 * productPath=getServletContext().getRealPath("/img"); FileOutputStream os=new
				 * FileOutputStream(new File(productPath,imageName)); IOUtils.copy(is, os);
				 * os.close(); is.close(); fi.delete(); String path=productPath+"/"+imageName;
				 * map.put("thumbnail", path); } }
				 */
			}
			
			BeanUtils.populate(product,map);
			//product.setSales_volume(0);
			//product.setCreate_time(new Date());
			SalerService service=new SalerService();
			service.update(product);
			request.getRequestDispatcher("/administrator/manage/alterSaler.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("修改销售员失败！");
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
