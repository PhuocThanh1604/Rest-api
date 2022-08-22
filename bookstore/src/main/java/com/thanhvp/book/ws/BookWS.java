/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thanhvp.book.ws;

import com.thanhvp.book.dao.Book;
import com.thanhvp.book.dao.BookDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 *
 * @author phuoc
 */
//nó chính là 1 cái Servlet trả về data thô JSON qua các hàm của class
// Vấn đềL: làm sao gọi được class này ??? gọi từ app khác , từ nơi khác 
// gọi qua URL -mapping da từng học ở bên JAVA Web
//Map url vào 1 method của 1 class nào đó !! đã học 
// tuy nhiên Tomcat nó chỉ quan tâm đén cái request noi chung 
// nhung trong project nay, code có 2 tp khac biet
// Serlet/JSP/HTML dung cho web truyền thống 
//phân API TRẢ về JSON dataa thô 
// lam soa tomcat phan biệt 
// ta phai cau hinh riêng cai url dac biet danh cho API
//URL truyen thong se danh cho web app
// URL truyền thống sẽ dành cho web aopp xưa nay
//ap chung ta dang la 2 trong 1: vua end-user, vua cho api 
//localhost:6969/tenapp - truyen thong lam web app 
//https://ten-mien-cuaminh.com/
//localhost:6969/ten-app/api/... -> gọi api của mình
//api/hay/sos/hay/
@Path("books")
//http://bookstore.com/ -> end-user
//http://bookstore.com/api/books -> đen đc class này!!1
public class BookWS {

    BookDAO dao = BookDAO.getInstance();

    @Context //day la cách Jersey giao tiếp với Tomcat
    UriInfo ui; // biến nay sẽ nói với Tomcat ,do Tomcat van quản lí Webapp nói chung
    //@GET: 100% giống duyệt web,goi ham qua URL, nhưng trả về JSON
    // có 1 hàm GET duoc dung lam amc dinh, cụ cham vao class này // ..api/books
    //thì se gọi hàm GET nay, nhung ham GET con lại thì đạt cho no 1duong dan phụ /api/books/duong-dan-phu

    @GET //so ham nay qua method GET của HTTP protocol
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloFromAPI() {
        return "This is 1st message comes from my own first API";
    }

    @GET //so ham nay qua method GET của HTTP protocol
    @Path("abook") //api/books/aboook
    @Produces(MediaType.APPLICATION_JSON)
    public Book getABook() {
        return new Book("8788427845641", "Đừng Chạy Theo Số Đông",
                "Kiên Trần", 1, 2020);
    }

    @GET //default GET
//    @Path("listBook")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getABooks() {

//        List<Book> books = new ArrayList();
//        books.add(new Book("8788427845641", "Đừng Chạy Theo Số Đông",
//                "Kiên Trần", 1, 2020));
//        
//         books.add(new Book("4018690253374","Sống Thực Tế Giữa Đời Thực Dụng",
//         "Dịch giả B.Nhung",1,2018));
//        return books;
        return dao.getAll();

    }

    //neu chi mun tim 1 cuoc sac theo ma so thi sao?
    //truyen tham so qua loi goi API, giống tham so ham, dua ma so cuon sach vào 
    @GET //default GET
    @Path("{isbn}") // tham số
    @Produces(MediaType.APPLICATION_JSON)
    public Book getABookByIsbn(@PathParam("isbn") String isbn) {
        return dao.getOne(isbn);

    }
    //Test:localhost:6969/bookstore/api/books/2425402340697

    //Hay nhát bắt đầu
    @POST  //Hàm này nhận vào 1 chuỗi JSON tương ưng cuốn sách và nó sẽ inseryt vào DB
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAbook(Book book) throws URISyntaxException {
        String newIsbn = dao.addOne(book); //Ham nay neu insert thanh cong 
        //sẽ trả về mà cuốn sách vua insert 
        //mac định khi insert thanh cong ,status 201
        URI uri = new URI(ui.getBaseUri() + "books/" + newIsbn);
        //tạo URL API GET /books/ma-cuon-sach-

        if (newIsbn == null) 
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        //Thành công -> status 201
                    
        return Response.created(uri).build(); // trả về theo url của cuốn sách 
    }

}
