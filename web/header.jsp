<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: GiangOggy
  Date: 01/08/2020
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


    <title>Parking Lot Header</title>

    <%@include file="css/css.jsp" %>

</head>
<body>
<%
    HttpSession s = request.getSession();
    User u = (User) s.getAttribute("user");
    if (u == null) {
%>
<header class="top_panel top_panel_custom top_panel_custom_12 top_panel_custom_header-fullwidth without_bg_image">
    <div class="vc_row wpb_row vc_row-fluid vc_custom_1522737638725 vc_row-o-equal-height vc_row-o-content-middle vc_row-flex shape_divider_top-none shape_divider_bottom-none sc_layouts_row sc_layouts_row_type_compact sc_layouts_row_fixed"
         style="top: auto;">
        <div class="wpb_column vc_column_container vc_col-sm-12 vc_col-md-8 sc_layouts_column sc_layouts_column_align_left sc_layouts_column_icons_position_left">
            <div class="vc_column-inner">
                <div class="wpb_wrapper">
                    <div class="sc_layouts_item" style=""><a href="#"
                                                             class="sc_layouts_logo sc_layouts_logo_default"><img
                            class="logo_image" src="//parkivia.ancorathemes.com/wp-content/uploads/2017/12/logo1.png"
                            alt="Parkivia" width="124" height="33"></a><!-- /.sc_layouts_logo --></div>
                    <div class="sc_layouts_item" style="">
                        <nav class="sc_layouts_menu sc_layouts_menu_default sc_layouts_menu_dir_horizontal menu_hover_fade hide_on_mobile inited"
                             data-animation-in="fadeInUpSmall"
                             data-animation-out="fadeOutDownSmall">
                            <ul
                                    class="sc_layouts_menu_nav inited sf-js-enabled sf-arrows"
                                    style="touch-action: pan-y;">
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>Home</span></a>

                                </li>
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>Features</span></a>

                                </li>
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>About Us</span></a>

                                </li>
                                <li
                                        style=""><a href="http://parkivia.ancorathemes.com/parking-options/"><span>Parking Options</span></a>
                                </li>
                                <li
                                        style="" data-width="123"><a
                                        href="http://parkivia.ancorathemes.com/location/"><span>Location</span></a></li>
                                <li class="menu-item menu-collapse" style="display: none;"><a href="#"
                                                                                              class="sf-with-ul trx_addons_icon-ellipsis-vert"></a>
                                    <ul class="submenu layouts_inited fadeOutDownSmall animated fast"
                                        style="display: none;"></ul>
                                </li>
                            </ul>
                        </nav><!-- /.sc_layouts_menu -->

                    </div>
                </div>
            </div>
        </div>
        <div class="wpb_column vc_column_container vc_col-sm-2 vc_col-md-4 vc_hidden-sm vc_hidden-xs sc_layouts_column sc_layouts_column_align_right sc_layouts_column_icons_position_left">
            <div class="vc_column-inner">
                <div class="wpb_wrapper">
                    <div class="sc_layouts_item">
                        <div
                                class="sc_layouts_login sc_layouts_menu sc_layouts_menu_default inited">
                            <ul class="sc_layouts_login_menu sc_layouts_menu_nav sc_layouts_menu_no_collapse prepared inited sf-js-enabled sf-arrows"
                                style="touch-action: pan-y;">
                                <li class="menu-item">
                                    <a href="http://localhost:8080/ParkingLot/login/loginForm.jsp"
                                       class="trx_addons_popup_link trx_addons_login_link  popup_inited"><span
                                            class="sc_layouts_item_icon sc_layouts_login_icon trx_addons_icon-user-alt"></span><span
                                            class="sc_layouts_item_details sc_layouts_login_details"><span
                                            class="sc_layouts_item_details_line1 sc_layouts_iconed_text_line1">Login</span></span></a>
                                </li>
                                <li class="menu-item">
                                    <a href="http://localhost:8080/ParkingLot/signup/SignUp.jsp">/ Sign Up</a>
                                </li>
                            </ul>
                        </div><!-- /.sc_layouts_login --></div>
                    <div class="sc_layouts_item sc_layouts_hide_on_mobile sc_layouts_hide_on_tablet">
                        <div
                                class="sc_layouts_iconed_text hide_on_tablet hide_on_mobile"><a href="tel:0800123456"
                                                                                                class="sc_layouts_item_link sc_layouts_iconed_text_link"><span
                                class="sc_layouts_item_icon sc_layouts_iconed_text_icon icon-icon_2"></span><span
                                class="sc_layouts_item_details sc_layouts_iconed_text_details"><span
                                class="sc_layouts_item_details_line2 sc_layouts_iconed_text_line2">0 (800) 123-456</span></span>
                            <!-- /.sc_layouts_iconed_text_details --></a></div><!-- /.sc_layouts_iconed_text --></div>
                </div>
            </div>
        </div>
    </div>
</header>

<%} else { %>
<header class="top_panel top_panel_custom top_panel_custom_12 top_panel_custom_header-fullwidth without_bg_image">
    <div class="vc_row wpb_row vc_row-fluid vc_custom_1522737638725 vc_row-o-equal-height vc_row-o-content-middle vc_row-flex shape_divider_top-none shape_divider_bottom-none sc_layouts_row sc_layouts_row_type_compact sc_layouts_row_fixed"
         style="top: auto;">
        <div class="wpb_column vc_column_container vc_col-sm-12 vc_col-md-8 sc_layouts_column sc_layouts_column_align_left sc_layouts_column_icons_position_left">
            <div class="vc_column-inner">
                <div class="wpb_wrapper">
                    <div class="sc_layouts_item" style=""><a href="#"
                                                             class="sc_layouts_logo sc_layouts_logo_default"><img
                            class="logo_image" src="//parkivia.ancorathemes.com/wp-content/uploads/2017/12/logo1.png"
                            alt="Parkivia" width="124" height="33"></a><!-- /.sc_layouts_logo --></div>
                    <div class="sc_layouts_item" style="">
                        <nav class="sc_layouts_menu sc_layouts_menu_default sc_layouts_menu_dir_horizontal menu_hover_fade hide_on_mobile inited"
                             data-animation-in="fadeInUpSmall"
                             data-animation-out="fadeOutDownSmall">
                            <ul
                                    class="sc_layouts_menu_nav inited sf-js-enabled sf-arrows"
                                    style="touch-action: pan-y;">
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>Home</span></a>

                                </li>
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>Features</span></a>

                                </li>
                                <li
                                        style=""><a href="#" class="sf-with-ul"><span>About Us</span></a>

                                </li>
                                <li
                                        style=""><a href="http://parkivia.ancorathemes.com/parking-options/"><span>Parking Options</span></a>
                                </li>
                                <li
                                        style="" data-width="123"><a
                                        href="http://parkivia.ancorathemes.com/location/"><span>Location</span></a></li>
                                <li class="menu-item menu-collapse" style="display: none;"><a href="#"
                                                                                              class="sf-with-ul trx_addons_icon-ellipsis-vert"></a>
                                    <ul class="submenu layouts_inited fadeOutDownSmall animated fast"
                                        style="display: none;"></ul>
                                </li>
                            </ul>
                        </nav><!-- /.sc_layouts_menu -->
                        <div class="sc_layouts_iconed_text sc_layouts_menu_mobile_button">
                            <a class="sc_layouts_item_link sc_layouts_iconed_text_link" href="#">
                                <span class="sc_layouts_item_icon sc_layouts_iconed_text_icon trx_addons_icon-menu"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wpb_column vc_column_container vc_col-sm-2 vc_col-md-4 vc_hidden-sm vc_hidden-xs sc_layouts_column sc_layouts_column_align_right sc_layouts_column_icons_position_left">
            <div class="vc_column-inner">
                <div class="wpb_wrapper">
                    <div class="sc_layouts_item">
                        <div
                                class="sc_layouts_login sc_layouts_menu sc_layouts_menu_default inited">
                            <ul class="sc_layouts_login_menu sc_layouts_menu_nav sc_layouts_menu_no_collapse prepared inited sf-js-enabled sf-arrows"
                                style="touch-action: pan-y;">
                                <li class="menu-item"><a href="http://localhost:8080/ParkingLot/LogoutController"
                                                         class="trx_addons_popup_link trx_addons_login_link  popup_inited"><span
                                        class="sc_layouts_item_icon sc_layouts_login_icon trx_addons_icon-user-alt"></span><span
                                        class="sc_layouts_item_details sc_layouts_login_details"><span
                                        class="sc_layouts_item_details_line1 sc_layouts_iconed_text_line1">Logout</span></span></a>
                                </li>
                            </ul>
                        </div><!-- /.sc_layouts_login --></div>
                    <div class="sc_layouts_item sc_layouts_hide_on_mobile sc_layouts_hide_on_tablet">
                        <div
                                class="sc_layouts_iconed_text hide_on_tablet hide_on_mobile"><a href="tel:0800123456"
                                                                                                class="sc_layouts_item_link sc_layouts_iconed_text_link"><span
                                class="sc_layouts_item_icon sc_layouts_iconed_text_icon icon-icon_2"></span><span
                                class="sc_layouts_item_details sc_layouts_iconed_text_details"><span
                                class="sc_layouts_item_details_line2 sc_layouts_iconed_text_line2">0 (800) 123-456</span></span>
                            <!-- /.sc_layouts_iconed_text_details --></a></div><!-- /.sc_layouts_iconed_text --></div>
                </div>
            </div>
        </div>
    </div>
</header>

<%}%>

</body>
</html>
