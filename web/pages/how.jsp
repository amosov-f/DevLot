<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content>
    <meta name="author" content>

    <title>How it works</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <link href="styles/styles.css" rel="stylesheet">
</head>

<body>

    <div class="blog-masthead">
        <div class="container">
            <nav class="blog-nav">
                <a class="blog-nav-item" href="form">Главная</a>
                <a class="blog-nav-item active" href="how">Как это работает?</a>
            </nav>
        </div>
    </div>

    <div class="container">

        <div class="blog-header">
            <h1 class="blog-title">Как это работает?</h1>
            <p class="lead blog-description">Все очень просто</p>
        </div>


        <p>Вы вводите данные предполагаемого квартала.</p>
        <img src="images/dir.PNG" class="img-responsive img-rounded" />
        <hr>
        <p>У нас есть база отобранных участков, на которых обучается система. База постоянно пополняется и точность возрастает.</p>

        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
            <img src="images/exc.PNG" class="img-responsive img-rounded col-lg-6 col-md-6 col-xs-6 col-sm-12"  />
            <img src="images/rgis.PNG" class="img-responsive img-rounded col-lg-6 col-md-6 col-xs-6 col-sm-12" />
        </div>
        <div class="row" style="margin: 0;" >
            <hr>
        </div>
        <p>Затем математическими методами мы расчитываем показатели, которые можно получить из участка.</p>
        <img src="images/prdprkt.PNG" class="img-responsive img-rounded" />

    </div>

    <div class="blog-footer">
        <p>CiBlock 2014</p><a href="mailto:ciblockinfo@gmail.com">ciblockinfo@gmail.com</a>
    </div>

</body>
</html>
