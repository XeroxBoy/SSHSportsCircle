<%@ page language="java" import="javax.imageio.ImageIO,java.awt.*,java.awt.image.BufferedImage,java.util.Random"
         pageEncoding="gb2312" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'validate.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>
<%
    response.setHeader("Cache-Control", "no-cache");
    int width = 60, height = 20;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //��ȡ����
    Graphics g = image.getGraphics();
    //�趨����ɫ
    g.setColor(new Color(100, 100, 100));
    g.fillRect(0, 0, width, height);
    //ȡ�����������֤��
    Random rnd = new Random();
    int randNum = rnd.nextInt(8999) + 1000;
    String randStr = String.valueOf(randNum);
    //����֤�����session
    session.setAttribute("randstr", randStr);
    //����֤����ʾ��ͼ����
    g.setColor(Color.black);
    g.setFont(new Font("", Font.PLAIN, 20));
    g.drawString(randStr, 10, 17); //10��17Ϊ���ʵĸ߶ȣ����
    for (int i = 0; i < 50; i++) {
        int x = rnd.nextInt(width);
        int y = rnd.nextInt(height);
        g.drawOval(x, y, 1, 1);
    }
    ImageIO.write(image, "JPEG", response.getOutputStream());
    out.clear();
    out = pageContext.pushBody(); //���������������͸��ͻ���
%>
<body></body>
</html>
