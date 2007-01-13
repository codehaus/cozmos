package com.thoughtworks.cozmos;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ModDavSvnProxyServlet extends HttpServlet {

    private String targetURL;
    private String newPageTemplate;
    private String passwordForGet;
    private String usernameForGet;
    private String host;

    public void init(ServletConfig servletConfig) throws ServletException {

        targetURL = servletConfig.getInitParameter("mod_dav_svn_url");
        newPageTemplate = servletConfig.getInitParameter("new_page_template_file");
        //usernameForGet = servletConfig.getInitParameter("username_for_get");
        //passwordForGet = servletConfig.getInitParameter("password_for_get");
        try {
            host = new URL(targetURL).getHost();
        } catch (MalformedURLException e) {
            throw new ServletException(e);
        }

        // WTF is setDefault global ? 
//        Authenticator.setDefault(new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                System.out.println("--> h " + getRequestingHost());
//                if (getRequestingHost().equals(host)) {
//                return new PasswordAuthentication(
//                        usernameForGet, passwordForGet.toCharArray());
//                } else {
//                    return null;
//                }
//            }
//        });
        super.init(servletConfig);


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();
        URL url = new URL(targetURL + path);

        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();

        int rc = urlc.getResponseCode();

        if (rc == HttpURLConnection.HTTP_NOT_FOUND) {
            url = new URL(targetURL + newPageTemplate);
            urlc = (HttpURLConnection) url.openConnection();
            rc = urlc.getResponseCode();
        }

        if (rc == HttpURLConnection.HTTP_OK) {
            String mimeType = getServletContext().getMimeType(path);
            resp.setContentType(mimeType);
            InputStream is = urlc.getInputStream();
            OutputStream os = resp.getOutputStream();
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) >= 0) {
                os.write(buffer, 0, length);
            }
        } else {
            resp.sendError(rc);
        }

    }

}
