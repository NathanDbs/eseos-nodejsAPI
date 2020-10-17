"use strict";

const pool = require('../db');

// checks for valid session for each incoming request
function checkSession (req, res) {
  if (!req.session.user || !req.cookies.user_key) {
    res.redirect("http://localhost:3000/login");
    return false;
  }
  return true;
}

// [GET] all users
const getUsers = (request, response) => {
  if(checkSession(request, response)) {
    pool.query(
      'SELECT * FROM comptes ORDER BY id ASC',
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).json(results.rows);
      }
    );
  }
};

// [GET] single user by id
const getUserById = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);

    pool.query(
      'SELECT * FROM comptes WHERE id = $1',
      [id],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).json(results.rows);
      }
    );
  }
};

// [POST] new user
const createUser = (request, response) => {
  if(checkSession(request, response)) {
    const {id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire} = request.body;

    pool.query(
      'INSERT INTO comptes (id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire) '
      +'VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10) RETURNING id',
      [id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(201)
          .send(`User added with ID: ${results.rows[0].id}`);
      }
    );
  }
};

// [PUT] update existing user
const updateUser = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);
    const {mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire} = request.body;

    pool.query(
      'UPDATE comptes SET SET mdp = $1, mail = $2, prenom = $3, nom = $4, rang = $5, nbPrestation = $6,'
			+ ' nbAchat = $7, nbProbleme = $8, commentaire = $9, WHERE id = $10',
      [mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire, id],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).send(`User modified with ID: ${id}`);
      }
    );
  }
};

// [DELETE] remove user
const deleteUser = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);

    pool.query(
      'DELETE FROM comptes WHERE id = $1',
      [id],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).send(`User deleted with ID: ${id}`);
      }
    );
  }
};

module.exports = {
  getUsers,
  getUserById,
  createUser,
  updateUser,
  deleteUser
};