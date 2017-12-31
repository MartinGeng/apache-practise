package com.sxgeng;

import org.apache.http.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;

public class HttpCore {

    @Test
    public void testHttpRequestMessage() {
        HttpRequest request = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);

        System.out.println(request.getRequestLine().getMethod());
        System.out.println(request.getRequestLine().getUri());
        System.out.println(request.getRequestLine().getProtocolVersion());
        System.out.println(request.getRequestLine().toString());
    }

    @Test
    public void testHttpResponseMessage() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
    }

    @Test
    public void testHttpCommonMessage() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a;path=/;domain=localhost");
        response.addHeader("Set-Cookie", "c1=b;path=\"/\",c3=c;domain=\"localhost\"");

        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);

        Header[] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);

        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        HeaderElementIterator elementIterator = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        while (elementIterator.hasNext()) {
            HeaderElement element = elementIterator.nextElement();
            System.out.println(element.getName() + "=" + element.getValue());
            NameValuePair[] params = element.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.println(" " + params[i]);
            }
        }
    }
}
