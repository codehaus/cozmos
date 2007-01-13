package com.thoughtworks.cozmos;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;

public class ModDavSvnProxyServlet2 extends HttpServlet {

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

        Socket socket = startGet(new URL(targetURL + path));

        InputStream is = socket.getInputStream();

        LineNumberReader lnr = new LineNumberReader(new InputStreamReader(is));

        boolean ok = isOk(lnr);

        if (!ok) {
            socket = startGet(new URL(targetURL + newPageTemplate));
            lnr = new LineNumberReader(new InputStreamReader(is));
            ok = isOk(lnr);

        }

        if (ok) {
            lnr.readLine(); // Date:
            lnr.readLine(); // Server:
            lnr.readLine(); // ETag:
            lnr.readLine(); // Accept-Ranges:
            int contentLength = getContentLen(lnr.readLine());
            lnr.readLine(); // Content-Type:
            lnr.readLine(); // end of header
            resp.setContentType(getServletContext().getMimeType(path));
            OutputStream os = resp.getOutputStream();
            int done = 0;
            while (done < contentLength) {
                int i = lnr.read();
                done++;
                os.write(i);
            }
            socket.close();
        }

    }

    private int getContentLen(String s) {
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        return Integer.parseInt(st.nextToken());
    }

    private boolean isOk(LineNumberReader lnr) throws IOException {
        return "HTTP/1.1 200 OK".equals(lnr.readLine());
    }

    private Socket startGet(URL url) throws IOException {
        Socket socket = new Socket(url.getHost(), 80);

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        pw.println("GET " + url.getPath() + " HTTP/1.1");
        pw.println("Host: " + url.getHost());
        pw.println();
        return socket;
    }

}
