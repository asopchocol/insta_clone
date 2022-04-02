import React from "react";

const Navbar = () => {
  return (
    <div className="h-14 w-full bg-white border-b border-gray-300">
      <div className="w-96 flex m-auto">
        <div className="col-span-1">instagram</div>
        <div className="col-span-1">
          <input type="text" />
        </div>
        <div className="col-span-1">아이콘들</div>
      </div>
    </div>
  );
};
export default Navbar;
