    <%--
      Created by IntelliJ IDEA.
      User: GiangOggy
      Date: 01/08/2020
      Time: 10:33
      To change this template use File | Settings | File Templates.
    --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <html>
        <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="generator" content="Codeply">
        <title>Forgot Password</title>
        <base target="_self">

        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">


        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <style>
        body{
        background-image: url(../img/img.jpg);
        }
        </style>
        </head>
        <body>
        <br>
        <br>
        <br>
        <br>

        <div class="container">
        <div class="row">
        <div class="row">
        <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-default">
        <div class="panel-body">
        <div class="text-center">
        <h3><i class="fa fa-lock fa-4x"></i></h3>
        <h2 class="text-center">Forgot Password?</h2>
        <p>You can reset your password here.</p>
        <div class="panel-body">

        <form class="form" method="post" action="http://localhost:8080/ParkingLot/ForgotPassController">
        <fieldset>
        <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><i
        class="glyphicon glyphicon-envelope color-blue"></i></span>

        <input id="emailInput" placeholder="emxample@gmail.com" name="email"
        class="form-control" type="email"
        oninvalid="setCustomValidity('Please enter a valid email address!')"
        onchange="try{setCustomValidity('')}catch(e){}" required="">
        </div>
        </div>
            <%
                                            String mess = (String) request.getAttribute("mess");
                                            if (mess != null) {
                                        %>
        <p style="color: red"><%=mess%>
        </p>
            <%}%>
        <div class="form-group">
        <input class="btn btn-lg btn-primary btn-block" value="Send My Password"
        type="submit">
        </div>
        <br>

        </fieldset>
        </form>

        </div>
        </div>
        </div>
        </div>
        </div>
        </div>
        </div>
        </div>

        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <script>

        </script>
        <script>
        // prevent navigation
        document.addEventListener("DOMContentLoaded", function () {
        var links = document.getElementsByTagName("A");
        for (var i = 0; i < links.length; i++) {
        links[i].addEventListener("click", function (e) {
        var href = this.getAttribute("href")

        if (!href) {
        return
        }

        if (href === '#') {
        // hash only ('#')
        console.debug('Internal nav allowed by Codeply');
        e.preventDefault()
        } else if (this.hash) {
        // hash with tag ('#foo')
        var element = null
        try {
        element = document.querySelector(this.hash)
        } catch (e) {
        console.debug('Codeply internal nav querySelector failed')
        }
        if (element) {
        // scroll to anchor
        e.preventDefault();
        const top = element.getBoundingClientRect().top + window.pageYOffset
        //window.scrollTo({top, behavior: 'smooth'})
        window.scrollTo(0, top)
        console.debug('Internal anchor controlled by Codeply to element:' + this.hash)
        } else {
        // allow javascript routing
        console.debug('Internal nav route allowed by Codeply');
        }
        } else if (href.indexOf("/p/") === 0 || href.indexOf("/v/") === 0) {
        // special multi-page routing
        console.debug('Special internal page route: ' + href)

        var l = href.replace('/p/', '/v/')

        // reroute
        e.preventDefault()
        var newLoc = l + '?from=internal'
        console.debug('Internal view will reroute to ' + newLoc)
        location.href = newLoc
        } else if (href.indexOf("./") === 0) {
        // special multi-page routing
        console.debug('Special internal ./ route: ' + href)

        var u = parent.document.URL.split("/")
        var pn = href.split("/")[1]
        var plyId = u[u.length - 1]

        if (plyId.indexOf('?from') > -1) {
        // already rerouted this
        console.debug('already rerouted')
        plyId = u[u.length - 2]
        }

        var l = plyId + '/' + pn

        console.debug(u)
        console.debug(pn)
        console.debug('l', l)

        // reroute
        e.preventDefault()
        var newLoc = '/v/' + l + '?from=internal'
        console.debug('Internal page will reroute to ' + newLoc)
        location.href = newLoc
        } else {
        // no external links
        e.preventDefault();
        console.debug('External nav prevented by Codeply');
        }
        //return false;
        })
        }
        }, null);
        </script>


        </body>
        </html>