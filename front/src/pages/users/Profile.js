import React, { Component, useState } from "react";
import "swiper/css";
import "swiper/css/pagination";
import Navbar from "../../components/Navbar";
import ProfileContent from "../../components/ProfileContent";
import ProfileFile from "../../components/Profilefile";
import ProfileMain from "../../components/ProfileMain";

const Profile = () => {
  return (
    <>
      <Navbar style={{ maxWidth: "200vh", minWidth: "200vh" }} />
      <main className="">
        <ProfileMain />
        <ProfileContent />
        <ProfileFile />
      </main>
    </>
  );
};
export default Profile;
