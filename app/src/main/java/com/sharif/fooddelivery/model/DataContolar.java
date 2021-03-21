package com.sharif.fooddelivery.model;

import java.util.List;

public class DataContolar {
    public static DataContolar instance;
 public static  DataContolar getInstance(){

     if(instance==null)
         instance = new DataContolar();


         return instance;

 }
 RestaurantInterface restaurantInterface;
 List<MenuItem>currentMenuItemList;

    public RestaurantInterface getRestaurantInterface() {
        return restaurantInterface;
    }

    public void setRestaurantInterface(RestaurantInterface restaurantInterface) {
        this.restaurantInterface = restaurantInterface;
    }

    public List<MenuItem> getCurrentMenuItemList() {
        return currentMenuItemList;
    }

    public void setCurrentMenuItemList(List<MenuItem> currentMenuItemList) {
        this.currentMenuItemList = currentMenuItemList;
    }
}

