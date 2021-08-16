<%@ page import="it.uniroma2.ispw.c3s.maps.model.*" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.ValidateAddressController" %>
<%@ page import="it.uniroma2.ispw.c3s.maps.controller.IconManagerController" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<!-- Controllo che l'utente abbia richiesto inserimento, aggiornamento o eliminazione icona -->

<%
    //
    String result_str1= "";
    String result_str2= "";
    String check_city= "";
    String check_road_nr1= "";
    String check_road_nr2= "";
    String check_idR1= "";
    String check_idR2= "";
    String city= "";
    String road="";
    String nr="";
    String idR="";
    String idR2="";
    String address = ""; %>

<%  boolean inserted = false;
    if (request.getParameter("InsertIconSubmit") != null) { %>
    <jsp:useBean id="infoInsertBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.InfoBean"/>
    <jsp:setProperty name="infoInsertBean" property="*"/>
<%
        boolean flag = true;

        city = infoInsertBean.getCity();
        if (city==null) {
            city="";
        }
        road = infoInsertBean.getRoad();
        if (road==null) {
            road="";
        }
        nr = infoInsertBean.getNr();
        if (nr==null) {
            nr="";
        }
        idR = infoInsertBean.getIdR();
        if (idR==null) {
            idR="";
        }
        if ( !(Pattern.matches("[a-zA-Z]+", road) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road) || road.isEmpty())){
            check_road_nr1 = "Valori non validi!";
            flag = false;
        } else {
            check_road_nr1 = "";
            if (!road.isEmpty()) {
                address = "via " + road + " ";
            }
        }
        if (!(Pattern.matches("[0-9]+", nr) || nr.isEmpty())) {
            check_road_nr2 = "Valori non validi!";
            flag = false;
        } else if (!nr.isEmpty() && (road.isEmpty() || !(Pattern.matches("[a-zA-Z]+", road) ||
                Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road)))) {
            check_road_nr2 = "Inserire via!";
            flag = false;
        } else {
            check_road_nr2 = "";
            if (!nr.isEmpty()) {
                address = address + nr + " ";
            }
        }
        if (!(Pattern.matches("[a-zA-Z]+", city) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",city) || city.isEmpty())) {
            check_city = "Valore non valido!";
            flag = false;
        } else if (city.isEmpty()) {
            check_city = "Valore obbligatorio!";
            flag = false;
        } else {
            check_city = "";
            address = address + city;
        }
        if (!(Pattern.matches("[0-9]+", idR) || idR.isEmpty())) {
            check_idR1 = "Valore non valido!";
            flag = false;
        } else if (idR.isEmpty()) {
            check_idR1 = "Valore obbligatorio!";
            flag = false;
        } else {
            check_idR1 = "";
        }
        if (!flag) {
        } else {
            LatLng point = ValidateAddressController.geocodeAddress(address);
            if (point != null) {
                try {
                    inserted = IconManagerController.insertIcon(point, infoInsertBean.getType().toLowerCase(),
                            infoInsertBean.getStatus().toLowerCase(), infoInsertBean.getEvaluation().toLowerCase(),
                                                                            Integer.parseInt(infoInsertBean.getIdR()));
                } catch (SQLException e) {
                    %> <script>
                        alert("Errore nella comunicazione con il database!");
                    </script>     <%
                } catch(ClassNotFoundException e){
                    %> <script>
                        alert("JDBC driver non trovato!");
                    </script>     <%
                }
            }
        }
        result_str1 = inserted ? "Inserimento avvenuto con successo!": "Inserimento non avvenuto," +
                                                                                     " controllare i dati inseriti!";
    } else if (request.getParameter("UpdateIconSubmit") != null) { %>
        <jsp:useBean id="infoUpdateBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.InfoBean"/>
        <jsp:setProperty name="infoUpdateBean" property="*"/>
<%
    boolean flag = true;

    city = infoUpdateBean.getCity();
    if (city==null) {
        city="";
    }
    road = infoUpdateBean.getRoad();
    if (road==null) {
        road="";
    }
    nr = infoUpdateBean.getNr();
    if (nr==null) {
        nr="";
    }
    idR = infoUpdateBean.getIdR();
    if (idR==null) {
        idR="";
    }
    if (!(Pattern.matches("[a-zA-Z]+", road) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road) || road.isEmpty())) {
        check_road_nr1 = "Valori non validi!";
        flag = false;
    } else {
        check_road_nr1 = "";
        if (!road.isEmpty()) {
            address = "via " + road + " ";
        }
    }
    if (!(Pattern.matches("[0-9]+", nr) || nr.isEmpty())) {
        check_road_nr1 = "Valori non validi!";
        flag = false;
    } else if (!nr.isEmpty() && (road.isEmpty() || !(Pattern.matches("[a-zA-Z]+", road) ||
            Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",road)))) {
        check_road_nr2 = "Inserire via!";
        flag = false;
    } else {
        check_road_nr2 = "";
        if (!nr.isEmpty()) {
            address = address + nr + " ";
        }
    }
    if (!(Pattern.matches("[a-zA-Z]+", city) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",city) || city.isEmpty())) {
        check_city = "Valore non valido!";
        flag = false;
    } else if (city.isEmpty()) {
        check_city = "Valore obbligatorio!";
        flag = false;
    } else {
        check_city = "";
        address = address + city;
    }
    if (!(Pattern.matches("[0-9]+", idR) || idR.isEmpty())) {
        check_idR1 = "Valore non valido!";
        flag = false;
    } else if (idR.isEmpty()) {
        check_idR1 = "Valore obbligatorio!";
        flag = false;
    } else {
        check_idR1 = "";
    }
    if (!flag) {
    } else {
        LatLng point = ValidateAddressController.geocodeAddress(address);
        if (point != null) {
            try {
                inserted = IconManagerController.updateIcon(point, infoUpdateBean.getType().toLowerCase(),
                        infoUpdateBean.getStatus().toLowerCase(), infoUpdateBean.getEvaluation().toLowerCase(),
                                                            Integer.parseInt(infoUpdateBean.getIdR()));
            } catch (SQLException e) {
            %> <script>
                alert("Errore nella comunicazione con il database!");
            </script>     <%
            } catch(ClassNotFoundException e){
            %> <script>
                alert("JDBC driver non trovato!");
            </script>     <%
            }
        }
    }
    result_str1 = inserted ? "Inserimento avvenuto con successo!": "Inserimento non avvenuto," +
                                                                                    " controllare i dati inseriti!";
    } else if (request.getParameter("DeleteIconSubmit") != null) { %>
        <jsp:useBean id="infoRemoveBean" scope="session" class="it.uniroma2.ispw.c3s.maps.view.InfoBean"/>
<%
        idR2 = request.getParameter("idR2");
        infoRemoveBean.setIdR(idR2);
        System.out.println(infoRemoveBean.getIdR());
        if (!(Pattern.matches("[0-9]+", idR2) || idR2.isEmpty())) {
            check_idR2 = "Valore non valido!";
        } else if (idR2.isEmpty()){
            check_idR2 = "Valore Obbligatorio";
        } else {
            check_idR2 = "";
            try {
                inserted = IconManagerController.removeIcon(Integer.parseInt(infoRemoveBean.getIdR()));
            } catch (SQLException e) {
            %> <script>
                alert("Errore nella comunicazione con il database!");
            </script>     <%
            } catch(ClassNotFoundException e){
            %> <script>
                alert("JDBC driver non trovato!");
            </script>     <%
            }
        }
        result_str2 = inserted ? "Rimozione avvenuta con successo!":
                                                                "Rimozione non avvenuta, controllare i dati inseriti!";
    }
%>

<!DOCTYPE html>
<style type="text/css">
    .button {background-color:white;border-radius:5px;border:
                                                        1px solid #D3D3D3; font-size:16px;position:relative;width:15%}
    .button:hover {background-color:#007AFF;color:white}
    .back_button {background-color:white;border-radius:5px;border:
                                                        1px solid #D3D3D3; font-size:16px;position:relative;right:180px}
    .back_button:hover {background-color:#007AFF;color:white}
</style>
<html><head>
    <title>F.E.R.S.A.</title>
</head>
<body class="Sito mappa">
<div class="container" align="center" style="font-size:16px;font-family:Tahoma, Geneva, sans-serif !important; margin-top:5%">
    <form action="iconManager.jsp" name="UseCase1Form" method="POST">
        <font size="5">Inserisci i dati richiesti</font>
        <br>
        <br>
        <br>
        Città*:
        <input type="text" name="city" value="<%=city%>">
        <br>
        <font color="red"><%= check_city %></font>
        <br>
        Via:
        <input type="text" name="road" value="<%=road%>">
        Nr:
        <input type="text" name="nr" value="<%=nr%>">
        <br>
        <font color="red"><%= check_road_nr1 %><%= check_road_nr2 %></font>
        <br>
        ID Risorsa*:
        <input type="text" name="idR" value="<%=idR%>">
        <br>
        <font color="red"><%= check_idR1 %></font>
        <br>
        Tipo*:
        <select name="type" style="font-size:17px;">
            <option value="Appartamento">Appartamento</option>
            <option value="Negozio">Negozio</option>
            <option value="Loft">Loft</option>
        </select>
        <br>
        <br>
        Valutazione*:
        <select name="evaluation" style="font-size:17px;">
            <option value="5">5</option>
            <option value="4">4</option>
            <option value="3">3</option>
            <option value="2">2</option>
            <option value="1">1</option>
        </select>
        <br>
        <br>
        Status*:
        <select name="status" style="font-size:17px;">
            <option value="Disponibile">Disponibile</option>
            <option value="Non disponibile">Non disponibile</option>
            <option value="Disponibile a breve">Disponibile a breve</option>
        </select>
        <br>
        <br>
        <font size="5">E scegli cosa vuoi fare</font>
        <br>
        <br>
        <input class="button" id="InsertIconSubmit" type="submit" name="InsertIconSubmit" value="Inserisci Icona">
        <input class="button" id="UpdateIconSubmit" type="submit" name="UpdateIconSubmit" value="Aggiorna Icona">
        <br>
        <br>
        <%= address %>
        <br>
        <%= result_str1 %>
        <br>
        <br>
        <hr>
        <br>
        <font size="5">Seleziona l'ID di una risorsa da rimuovere</font>
        <br>
        <br>
        ID Risorsa*:
        <input type="text" name="idR2">
        <input class="button" id="DeleteIconSubmit" type="submit" name="DeleteIconSubmit" value="Elimina Icona">
        <br>
        <br>
        <font color="red"><%= check_idR2 %></font>
        <br>
        <br>
        <%= result_str2 %>
        <br>
        <br>
        <button class="button" type="button" onclick="location.href='index.html'">Indietro</button>
    </form>
</div>

</body></html>