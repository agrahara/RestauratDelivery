package com.restaurant.online.entity;

public class View {

    //Using View to get Control of fields exposed control.
    public static class Public { }
    public static class ExtendedPublic extends Public { }
    public static class Internal extends ExtendedPublic { }

}
