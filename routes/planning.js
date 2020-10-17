"use strict";

const express = require('express');
const router = new express.Router();

const Planning = require('../models/planning');

router.route('/')
  .get(Planning.getPlannings)       // [GET] all plannings
  .post(Planning.createPlanning);   // [POST] new planning

router.route('/:id')
  .get(Planning.getPlanningById)    // [GET] single planning by id
  .put(Planning.updatePlanning)     // [PUT] update existing planning
  .delete(Planning.deletePlanning); // [DELETE] remove planning

module.exports = router;
