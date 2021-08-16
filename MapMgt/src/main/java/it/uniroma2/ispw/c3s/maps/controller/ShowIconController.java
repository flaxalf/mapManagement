package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.model.*;


import java.util.ArrayList;
import java.util.List;

public class ShowIconController {

    public static Map showIcon(Icon icon){
        List<Icon> list = new ArrayList<>();
        list.add(icon);
        return new Map(icon.getLatlong(), 15, list);
    }
    public static Map showIcon(int number){
        Map mv = null;
        switch (number){
            case 1:
                mv = showIcon(new Icon(ValidateAddressController.geocodeAddress("roma via del corso 50"),new IconType("loft"),
                        new Status("disponibile"),new Evaluation("3"), 5));
                break;
            case 2:
                mv = showIcon(new Icon(ValidateAddressController.geocodeAddress("milano via cesare battisti 50"),new IconType("appartamento"),
                        new Status("non disponibile"),new Evaluation("5"), 6));
                break;
            case 3:
                mv = showIcon(new Icon(ValidateAddressController.geocodeAddress("roma via cambridge 50"),new IconType("loft"),
                        new Status("disponibile a breve"),new Evaluation("4"), 7));
                break;

        }
        return mv;
    }
}
