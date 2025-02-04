import axios from "axios";

export default axios.create({
  baseURL: "http://tutorials2025crc.us-east-1.elasticbeanstalk.com/api/v1/tutorials",
  headers: {
    "Content-type": "application/json"
  }
});