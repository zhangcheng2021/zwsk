package com.hsmap.yuezhihui.admin;

import com.hsmap.yuezhihui.admin.controller.review.ReviewCurrentController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class BatdhControllerPdf extends BootTest {
    @Autowired
    private ReviewCurrentController reviewCurrentController;
    private MockHttpServletResponse response;
    private MockHttpServletRequest request;

    @Test
    public void batchPdf() {
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
//        Integer[] ids = new Integer[]{234, 225, 135, 61, 241, 126, 24, 208, 221, 127, 7, 239, 15, 186, 214, 79, 115, 18, 139, 26, 198, 71, 8, 117, 13, 95, 215, 116, 46, 22, 31, 148, 28, 218, 10, 147, 182, 203, 88, 184, 109, 177, 12, 56, 258, 157, 99, 187, 86, 21, 23, 68, 137, 173, 32, 189, 123, 151, 227, 200, 80, 96, 136, 163, 179, 193, 55, 156, 134, 183, 144, 5, 114, 192, 197, 146, 202, 195, 209, 34, 207, 149, 210, 240, 226, 78, 204, 87, 11, 169, 50, 91, 167, 20, 47, 205, 140, 230, 4, 164, 217, 130, 58, 168, 44, 206, 107, 94, 170, 124, 220, 159, 216, 185, 201, 93, 97, 211, 49, 73, 53, 253, 181, 236, 245, 246, 108, 90, 194, 175, 213, 63, 190, 100, 188, 17, 52, 180, 172, 14, 83, 228, 142, 25, 244, 212, 178, 160, 191, 171, 268, 219, 267, 43, 76, 158, 243, 85, 74, 255, 152, 231, 72, 133, 153, 196, 128, 3, 143, 57, 111, 154, 39, 67, 1, 38, 42, 60, 89, 129, 9, 62, 59, 98, 45, 138, 270, 222, 104, 102, 119, 254, 33, 69, 118, 125, 247, 199, 176, 106, 272, 229, 54, 113, 132, 36, 112, 6, 145, 162, 242, 27, 2, 265, 174, 161, 101, 122, 121};
        Integer[] ids = new Integer[]{135, 61};
        for (Integer i : ids) {
            System.out.println("pdf : " + i);
//            String ret = reviewCurrentController.pdf(i,response,request);
//            System.out.println("pdf.ret : " + ret);
        }
        /*String ret = reviewCurrentController.pdf(191);
        System.out.println(ret);
        String ret2 = reviewCurrentController.pdf(208);
        System.out.println(ret2);
        String ret3 = reviewCurrentController.pdf(221);
        System.out.println(ret3);
        String ret4 = reviewCurrentController.pdf(71);
        System.out.println(ret4);*/
    }

    @Test
    public void batchPdfs() {
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest();
        Integer[] ids = new Integer[]{234,61,241,127};

//            System.out.println("pdf : " + ids);
        String ret = reviewCurrentController.pdfs(0, response, request);
        System.out.println("pdf.ret : " + ret);

    }
}
