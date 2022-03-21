const functions = require('firebase-functions');
const cors = require('cors')({origin: true});
const request = require('request');
exports.apicall = functions.https.onRequest((req, response) => {
  cors(req, response, () => {
    request(`https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/hideonbush?api_key=RGAPI-674f74e7-1c47-41ca-bdd5-6bad2a4ea7d9`, function (error, res, body) {
      response.send(res);
    });
  })
});