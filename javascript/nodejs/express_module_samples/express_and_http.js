
import http from "http";
import express from "express";

http.createServer(express)
    .listen(52273, () => {
        console.log("Server Running Express and Http")
    });