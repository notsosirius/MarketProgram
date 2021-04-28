package com.javsrc.web.controller.administrator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.LogFactory;

import com.javsrc.entity.Product;
import com.javsrc.service.PhotoService;
import com.javsrc.service.ProductService;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/administrator/addProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			addDVDinfo(request, response);
		}catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("保存商品失败！");
		}
		
	}

	private static void addDVDinfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        List<String> list=new ArrayList<String>();
        String filename=PhotoService.getPhotoNewName();
        ServletContext servletContext=null;
        servletContext=request.getSession().getServletContext();
        try {
			//数据库中存储格式:***.jpg
			//第一步:获取页面上上传的图片资源
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				String str = "javax.servelet.context.tempdir";
				File repository = (File) servletContext.getAttribute(str);
				factory.setRepository(repository);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Product product = new Product();
				Map<String, Object> map = new HashMap<String, Object>();
				boolean isLoadToSQL = false;
				for (FileItem item : items) {
					String fieldName = item.getFieldName();
					if (!item.isFormField()) {
						//判断后缀名是否是jpg
						if (PhotoService.isJpg(item)) {
							isLoadToSQL = PhotoService.saveFile(item, filename);
							map.put("thumbnail", filename);
						} else {
							System.out.println("后缀格式有误，保存文件失败");
						}
					} else {
						/*获取表单中的非文件值
						表单中的空间name值
						System.out.println("name值:  "+item.getFieldName());
						该name值空间中的value值
						System.out.println(item.getString("UTF-8"));*/
						/* list.add(item.getString("UTF-8")); */
						String stringValues = item.getString("utf-8");
						map.put(fieldName, stringValues);
					}
				}
				BeanUtils.populate(product, map);
				product.setSales_volume(0);
				product.setCreate_time(new Date());
				ProductService service = new ProductService();
				service.save(product);
				request.getRequestDispatcher("/administrator/product/add.jsp").forward(request, response);
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("保存商品失败！");
		}
		
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
