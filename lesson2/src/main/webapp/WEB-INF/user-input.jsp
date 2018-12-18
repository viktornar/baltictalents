<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User input</title>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
          integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
</head>
<body>
<div style="width: 80%;margin: 20px auto 0 auto">
    <h2>User Input Form</h2>
    <form method="post" action="user-input" class="pure-form pure-form-stacked">
        <fieldset>
            <legend>Personal Particular</legend>

            <div>
                <label for="username">
                    Name: <input type="text" name="username" id="username"/>
                </label>

                <label for="password">
                    Password: <input type="password" name="password" id="password"/>
                </label>

            </div>

            <div style="margin: 10px 0 10px 0">
                Gender:
            </div>
            <div style="margin: 0 0 10px 0">
                <input type="radio" name="gender" value="m" checked style="margin-right: 5px"/>Male
                <input type="radio" name="gender" value="f" style="margin-left: 10px; margin-right: 5px" />Female
            </div>

            <div style="margin: 0 0 10px 0">
                <label for="age">
                    Age:
                    <select name="age" id="age">
                        <option value="1">&lt; 1 year old</option>
                        <option value="99">1 to 99 years old</option>
                        <option value="100">&gt; 99 years old</option>
                    </select>
                </label>
            </div>
        </fieldset>

        <fieldset>
            <legend>Languages</legend>
            <input type="checkbox" name="language" value="java" checked style="margin-right: 5px" />Java
            <input type="checkbox" name="language" value="c" style="margin-left: 10px; margin-right: 5px" />C/C++
            <input type="checkbox" name="language" value="cs" style="margin-left: 10px; margin-right: 5px"/>C#
        </fieldset>

        <input type="submit" value="submit" class="pure-button pure-button-primary"/>
    </form>
</div>
</body>
</html>
