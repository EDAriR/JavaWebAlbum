package com.diary.controller;

import com.diary.model.BabyService;
import com.diary.model.BabyVO;
import com.diary.model.PhotoService;
import com.diary.model.PhotoVO;

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


        if ("getMemBaby_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            System.out.println("getMemBaby_For_Display in PhotoServlet action: " + action);

            HttpSession session = req.getSession();

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String mem_no = req.getParameter("mem_no");

                System.out.println("getMemBaby_For_Display mem_no  ===>" + mem_no);
                session.setAttribute("mem_no",mem_no);

                /***************************2.�}�l�d�߸��*****************************************/

                BabyService babySvc = new BabyService();
                List<BabyVO> babylist = babySvc.getByMemNo(mem_no);
                PhotoService photoSvc = new PhotoService();
                List<PhotoVO> photoList = null;
                HashSet<List<PhotoVO>> babySet = null;

                if (babylist.size() == 0) {
                    errorMsgs.add("�z�S���p��");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/babylist.size() == 0select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                for (BabyVO list:babylist) {

                    photoList = photoSvc.findBybaby(list.getBaby_no());

                    System.out.println("baby_no: " + mem_no);
                    System.out.println(list.getBaby_aka()+ "  photoList: " + photoList.size());
                    babySet.add(photoList);

                    if (photoList == null) {
                        errorMsgs.add(list.getBaby_aka() + "�d�L���");
                    }
                }

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("babySet", babySet);
                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getbaby_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            System.out.println("getbaby_For_Display in PhotoServlet action: " + action);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String str = req.getParameter("baby_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("�п�JBABY�s��");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                String baby_no = null;
                try {
                    baby_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("BABY�s���榡�����T");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************2.�}�l�d�߸��*****************************************/
                PhotoService photoSvc = new PhotoService();
                List<PhotoVO> photoList = photoSvc.findBybaby(baby_no);
                System.out.println("baby_no: " + baby_no);
                System.out.println("photoList: " + photoList);
                if (photoList == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("photoList", photoList); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/frontend/diary/photo/listBabyPhoto_backupV1.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }


        if ("getOne_For_Update".equals(action)) {

        }


        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  

        }


        if ("utility.delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j �� �i /emp/listEmps_ByCompositeQuery.jsp�j

            try {
                /***************************1.�����ШD�Ѽ�***************************************/
                String pho_no = req.getParameter("pho_no");
                System.out.println(pho_no);

                /***************************2.�}�l�R�����***************************************/
                PhotoService photoSvc = new PhotoService();
                PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
                photoSvc.deletePhoto(pho_no);
                System.out.println(photoVO);

                /***************************3.�R������,�ǳ����(Send the Success view)***********/

                String url = requestURL;
                RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
                successView.forward(req, res);
                System.out.println(url);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("listEmps_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {

                /***************************1.�N��J����ରMap**********************************/
                //�ĥ�Map<String,String[]> getParameterMap()����k
                //�`�N:an immutable java.util.Map
                //Map<String, String[]> map = req.getParameterMap();
                HttpSession session = req.getSession();
//                Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
                List<PhotoVO> lsit = (List<PhotoVO>) session.getAttribute("map");
//                if (req.getParameter("whichPage") == null) {
//                    HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
//                    HashMap<String, String[]> map2 = new HashMap<String, String[]>();
//                    map2 = (HashMap<String, String[]>) map1.clone();
//                    session.setAttribute("map", map2);
//                    map = (HashMap<String, String[]>) req.getParameterMap();
//                }

                /***************************2.�}�l�ƦX�d��***************************************/
                PhotoService empSvc = new PhotoService();
                List<PhotoVO> list = empSvc.getAll();

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
                req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
                RequestDispatcher successView = req.getRequestDispatcher("/emp/listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/select_page.jsp");
                failureView.forward(req, res);
            }
        }
    }
}