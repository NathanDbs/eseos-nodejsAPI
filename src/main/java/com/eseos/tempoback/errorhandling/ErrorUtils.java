package com.eseos.tempoback.errorhandling;

public class ErrorUtils {

    public static final String ENVOI_MAIL = "L'envoi du mail a échoué";

    public static final String AUTHORITY_NAME_NULL = "Vous devez renseigner le rôle de l'utilisateur";
    public static final String AUTHORITY_NAME_SIZE = "Le rôle de l'utilisateur doit faire entre 1 et 50 caractères";
    public static final String AUTHORITY_NAME_INVALID = "Le rôle de l'utilisateur est inconnu";

    public static final String USER_LOGIN_EMPTY = "Vous devez renseigner le login de l'utilisateur";
    public static final String USER_LOGIN_SIZE = "Le login de l'utilisateur ne doit pas dépasser 50 caractères";
    public static final String USER_LOGIN_NOT_UNIQUE = "Le login de l'utilisateur existe déjà";
    public static final String USER_PASSWORD_EMPTY = "Vous devez renseigner le mot de passe de l'utilisateur";
    public static final String USER_PASSWORD_SECURITY = "Le mot de passe de l'utilisateur n'est pas assez sécurisé";
    public static final String USER_FIRST_NAME_EMPTY = "Vous devez renseigner le prénom de l'utilisateur";
    public static final String USER_FIRST_NAME_SIZE = "Le prénom de l'utilisateur ne doit pas dépasser 255 caractères";
    public static final String USER_LAST_NAME_EMPTY = "Vous devez renseigner le nom de l'utilisateur";
    public static final String USER_LAST_NAME_SIZE = "Le nom de l'utilisateur ne doit pas dépasser 255 caractères";
    public static final String USER_EMAIL_EMPTY = "Vous devez renseigner l'adresse mail de l'utilisateur";
    public static final String USER_EMAIL_SIZE = "L'adresse mail de l'utilisateur ne doit pas dépasser 255 caractères";
    public static final String USER_EMAIL_FORMAT = "Le format de l'adresse mail de l'utilisateur est invalide";
    public static final String USER_NOT_FOUND = "Aucun utilisateur trouvé pour cet identifiant";
    public static final String ADD_USER_FORBIDDEN = "Vous n'êtes pas autorisé à effectuer cette opération, permission refusée";

    public static final String DELETE_USER_FORBIDDEN = "Impossible de supprimer cet utilisateur";

    public static final String RESET_PASSWORD_INVALID_TOKEN = "Le lien de réinitialisation est invalide ou expiré";

    public static final String HORAIRE_DATE_EMPTY = "La date ne peut pas être vide";
    public static final String HORAIRE_CRENEAU_EMPTY = "Le créneau ne peut pas être vide";
    public static final String HORAIRE_NBMEMBRES_EMPTY = "Le nombre de membres ne peut pas être vide";
}
