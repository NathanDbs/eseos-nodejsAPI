"use strict";

const pool = require('../db');

// checks for valid session for each incoming request
function checkSession (req, res) {
  if (!req.session.user || !req.cookies.user_key) {
    res.redirect("https://eseos.herokuapp.com:3000/login");
    return false;
  }
  return true;
}

// [GET] all plannings
const getPlannings = (request, response) => {
  if(checkSession(request, response)) {
    pool.query(
      'SELECT * FROM planning ORDER BY id ASC',
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).json(results.rows);
      }
    );
  }
};

// [GET] single planning by id
const getPlanningById = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);

    pool.query(
      'SELECT * FROM planning WHERE id = $1',
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

// [POST] new planning
const createPlanning = (request, response) => {
  if(checkSession(request, response)) {
    const {id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire} = request.body;

    pool.query(
      'INSERT INTO planning (id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire) '
      +'VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10) RETURNING id',
      [id, mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(201)
          .send(`Planning added with ID: ${results.rows[0].id}`);
      }
    );
  }
};

// [PUT] update existing planning
const updatePlanning = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);
    const {mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire} = request.body;

    pool.query(
      'UPDATE planning SET SET mdp = $1, mail = $2, prenom = $3, nom = $4, rang = $5, nbPrestation = $6,'
			+ ' nbAchat = $7, nbProbleme = $8, commentaire = $9, WHERE id = $10',
      [mdp, mail, prenom, nom, rang, nbPrestation, nbAchat, nbProbleme, commentaire, id],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).send(`Planning modified with ID: ${id}`);
      }
    );
  }
};

// [DELETE] remove planning
const deletePlanning = (request, response) => {
  if(checkSession(request, response)) {
    const id = parseInt(request.params.id);

    pool.query(
      'DELETE FROM planning WHERE id = $1',
      [id],
      (error, results) => {
        if (error) {
          throw error;
        }
        response.status(200).send(`Planning deleted with ID: ${id}`);
      }
    );
  }
};

module.exports = {
  getPlannings,
  getPlanningById,
  createPlanning,
  updatePlanning,
  deletePlanning
};