<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Réinitialisation de mot de passe</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
        p {
            padding: 0 15px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<p>Bonjour ${firstName},</p>

<p>
    Vous avez demandé la réinitialisation de votre mot de passe. Veuillez cliquer sur le lien ci-dessous valable pendant ${expirationTime}
    minutes et enregistrez votre nouveau mot de passe :
</p>

<p><a href="${url}">Réinitialiser mon mot de passe</a></p>

<p>Cordialement,</p>

<p>Tempo - eseos</p>

</body>
</html>