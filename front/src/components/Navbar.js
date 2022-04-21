import React from "react";
import { FaSearch, FaHome } from "react-icons/fa";
import { FiSend, FiXSquare, FiCompass, FiHeart } from "react-icons/fi";
import { CgProfile } from "react-icons/cg";
import { Link } from "react-router-dom";

const Navbar = () => {
  const Search = {
    backgroundcolor: "#dcdada",
  };

  return (
    <div className=" w-full bg-white border-b border-gray-300 ">
      <div className="w-96 flex m-auto space-x-1">
        <div className=" col-span-1 my-2 mr-4">instagram</div>
        <FaSearch className="col-span-1 m-auto"></FaSearch>
        <div className="col-span-1 my-auto">
          <input
            classname="border border-gray-300 "
            type="text"
            style={Search}
            placeholder="검색"
            name="search"
          />
        </div>
        <Link className="col-span-1 m-auto scale-100" to="/">
          <FaHome></FaHome>
        </Link>

        <Link className="col-span-1  m-auto scale-100" to="/login">
          <FiSend></FiSend>
        </Link>

        <FiXSquare className="col-span-1 m-auto scale-100"></FiXSquare>
        <FiCompass className="col-span-1 m-auto scale-100"></FiCompass>
        <FiHeart className="col-span-1 m-auto scale-100"></FiHeart>
        <CgProfile className="col-span-1 m-auto scale-100"></CgProfile>
      </div>
    </div>
  );
};
export default Navbar;
