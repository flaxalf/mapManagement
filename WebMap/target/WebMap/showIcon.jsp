<%@ page import="it.uniroma2.ispw.c3s.maps.model.Map" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.ShowIconController" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!-- Controllo che l'utente abbia richiesto tramite bottone di mostrare la mappa con l'icona -->

<%
    if (request.getParameter("showExampleSubmit") != null) { %>
        <jsp:useBean id="mapBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.MapBean"/>
    <%
        int value_icons = Character.getNumericValue(request.getParameter("showExampleSubmit").charAt(0));
        Map mv = ShowIconController.showIcon(value_icons);
        mapBean.setCenter(mv.getCenter());
        mapBean.setIconList(mv.getIconList());
        mapBean.setZoom(mv.getZoom());

    %>
        <jsp:forward page="map.jsp" />
    <%
    }
%>

<!DOCTYPE html>
<style type="text/css">
    .button {background-color:white;border-radius:5px;border:
                                                        1px solid #D3D3D3; font-size:16px;position:relative;width:20%}
    .button:hover {background-color:#007AFF;color:white}
</style>
<html><head>
    <title>F.E.R.S.A.</title>
</head>
<body class="Sito mappa">
<div class="container" align="center" style="font-size:16px;font-family:Tahoma, Geneva, sans-serif !important; margin-top:10%">
    <form action="showIcon.jsp" name="UseCase1Form" method="POST">
        <font size="5">Cliccare sui bottoni sottostanti per <br> visualizzare l'icona corrispondente in mappa</font>
        <br>
        <br>
        <br>
        <input class="button"  id="showExample1Submit" type="submit"
                                                            name="showExampleSubmit" value="1. Roma Via del Corso 50">
        <br>
        <br>
        <input class="button"  id="showExample2Submit" type="submit"
                                                    name="showExampleSubmit" value="2. Milano Via Cesare Battisti 50">
        <br>
        <br>
        <input class="button"  id="showExample3Submit" type="submit"
                                                            name="showExampleSubmit" value="3. Roma Via Cambridge 50">
    </form>
    <br>
    <br>
    <button class="button" type="button" onclick="location.href='index.html'">Indietro</button>
</div>

</body></html>