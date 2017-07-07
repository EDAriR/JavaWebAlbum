package com.diary.controller;

import com.diary.model.*;
import com.sun.javafx.collections.MappingChange.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/PhotoServlet.do")
public class PhotoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        if ("getMemBaby_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            System.out.println("getMemBaby_For_Display in PhotoServlet action: " + action);

            HttpSession session = req.getSession();

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String mem_no = req.getParameter("mem_no");

                System.out.println("getMemBaby_For_Display mem_no  ===>" + mem_no);
                session.setAttribute("mem_no",mem_no);

                /***************************2.開始查詢資料*****************************************/

                List<PhotoVO> photoList = null;
                Map<String,List<PhotoVO>> babyMap = null;
                BabyService babySvc = new BabyService();
               
                List<BabyVO> babylist = babySvc.getByMemNo(mem_no);
                System.out.println("babylist: " + babylist.size());
                
                if (babylist.size() == 0) {
                    errorMsgs.add("您沒有小孩快去生一個吧!");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }
                
                for (BabyVO list:babylist) {
                System.out.println("list.getBaby_no()" + list.getBaby_no());
                }
                
                PhotoService photoSvc = new PhotoService();
                for (BabyVO list:babylist) {
                	
                	System.out.println("list.getBaby_no()" + list.getBaby_no());
                	String str = list.getBaby_no();

                    photoList = photoSvc.findBybaby(list.getBaby_no());

                    System.out.println(list.getBaby_aka()+ "  photoList: " + photoList.size());
                    
                   
                    System.out.println("babySet.........." + babyMap.toString()+ str);

//                    if (photoList == null) {
//                        errorMsgs.add(list.getBaby_aka() + "查無資料");
//                    }
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("photoList", photoList);
                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getbaby_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            System.out.println("getbaby_For_Display in PhotoServlet action: " + action);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String str = req.getParameter("baby_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("請輸入BABY編號");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                String baby_no = null;
                try {
                    baby_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("BABY編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************2.開始查詢資料*****************************************/
                PhotoService photoSvc = new PhotoService();
                List<PhotoVO> photoList = photoSvc.findBybaby(baby_no);
                System.out.println("baby_no: " + baby_no);
                System.out.println("photoList: " + photoList);
                if (photoList == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("photoList", photoList); // 資料庫取出的empVO物件,存入req
                String url = "/frontend/diary/photo/listBabyPhoto_backupV1.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }


        if ("getOne_For_Update".equals(action)) {

        }


        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

        }


        if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

            try {
                /***************************1.接收請求參數***************************************/
                String pho_no = req.getParameter("pho_no");
                System.out.println(pho_no);

                /***************************2.開始刪除資料***************************************/
                PhotoService photoSvc = new PhotoService();
                PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
                photoSvc.deletePhoto(pho_no);
                System.out.println(photoVO);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/

                String url = requestURL;               
                RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
                successView.forward(req, res);
                System.out.println(url);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
           
        }
    }
}
