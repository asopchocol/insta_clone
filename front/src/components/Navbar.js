import React from "react";
import { FaSearch, FaHome } from "react-icons/fa";
import { FiSend, FiXSquare, FiCompass, FiHeart } from "react-icons/fi";
import { CgProfile } from "react-icons/cg";
import { Link } from "react-router-dom";

const Navbar = (props) => {
  const Search = {
    backgroundColor: "#eaeaea",
    padding: "5px",
    width: "250px",
  };
  const { navStyle } = props;

  const iconClassName = () => {
    return "my-auto px-3 scale-125";
  };

  return (
    <div className=" w-full bg-white border-b border-gray-300 pb-2 justify-items-center">
      <div
        className="w-full flex m-auto space-x-1 grid grid-cols-10 flex-nowrap"
        style={navStyle}
      >
        <div className="col-start-2 col-span-2 flex flex-row">
          <Link className="my-auto mr-4 text-3xl" to="/">
            instagram
          </Link>
        </div>

        <div className="col-start-5 col-span-3 flex flex-row">
          <div className="my-auto  mr-3">
            <FaSearch />
          </div>
          <div className="py-3">
            <input
              classname="border border-gray-300"
              type="text"
              style={Search}
              placeholder="검색"
              name="search"
            />
          </div>
        </div>
        <div className="col-span-3 flex flex-row flex-nowrap">
          <Link className={iconClassName()} to="/">
            <FaHome></FaHome>
          </Link>

          <Link className={iconClassName()} to="/login">
            <FiSend></FiSend>
          </Link>

          <button className={iconClassName()}>
            <FiXSquare />
          </button>

          <button type="button" className={iconClassName()}>
            <FiCompass />
          </button>

          <button className={iconClassName()}>
            <FiHeart />
          </button>
          <Link className={iconClassName()} to="/profile">
            <CgProfile />
          </Link>
        </div>
      </div>
    </div>
  );
};
export default Navbar;
