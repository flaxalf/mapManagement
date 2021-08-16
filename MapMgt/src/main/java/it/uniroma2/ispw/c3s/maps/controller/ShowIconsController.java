package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.model.*;


import java.util.ArrayList;
import java.util.List;

public class ShowIconsController {
    public static Map showIcons(List<Icon> icons){
        return new Map(icons.get(0).getLatlong(), 10, icons);
    }

    public static Map showIcons(int number){
        Map mv = null;
        List<Icon> list = new ArrayList<>();
        switch (number){
            case 1:
                list.add(new Icon(ValidateAddressController.geocodeAddress("roma via del corso 50"),new IconType("loft"),
                        new Status("disponibile"),new Evaluation("5"), 5));
                list.add(new Icon(ValidateAddressController.geocodeAddress("roma via cambridge 50"),new IconType("appartamento"),
                        new Status("non disponibile"),new Evaluation("2"), 7));
                list.add(new Icon(ValidateAddressController.geocodeAddress("roma frattina 25"),new IconType("appartamento"),
                        new Status("disponibile"),new Evaluation("3"), 8));
                list.add(new Icon(ValidateAddressController.geocodeAddress("roma corso vittorio emanuele 30"),new IconType("loft"),
                        new Status("disponibile a breve"),new Evaluation("4"), 8));
                mv = showIcons(list);
                break;
            case 2:
                list.add(new Icon(ValidateAddressController.geocodeAddress("milano via diaz 50"),new IconType("appartamento"),
                        new Status("disponibile"),new Evaluation("2"), 6));
                list.add(new Icon(ValidateAddressController.geocodeAddress("milano via allodola 15"),new IconType("appartamento"),
                        new Status("non disponibile"),new Evaluation("5"), 9));
                list.add(new Icon(ValidateAddressController.geocodeAddress("milano via belgioioso 10"),new IconType("loft"),
                        new Status("disponibile"),new Evaluation("5"), 10));
                mv = showIcons(list);
                break;
            case 3:
                list.add(new Icon(ValidateAddressController.geocodeAddress("napoli via benedetto croce 15"),new IconType("appartamento"),
                        new Status("disponibile"),new Evaluation("2"), 25));
                list.add(new Icon(ValidateAddressController.geocodeAddress("napoli via maddaloni 30"),new IconType("appartamento"),
                        new Status("non disponibile"),new Evaluation("5"), 26));
                list.add(new Icon(ValidateAddressController.geocodeAddress("napoli via calabritto  30"),new IconType("appartamento"),
                        new Status("disponibile a breve"),new Evaluation("3"), 25));
                mv = showIcons(list);
                break;

        }
        return mv;
    }
}


