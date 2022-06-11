import React, { Component, useState } from "react";

const ProfileFile = () => {
  const imageDatas = [
    "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fdf%2Fcb%2Fd0%2Fdfcbd0982731d27df1ee3d6d3f584771.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMTNfNSAg%2FMDAxNjQ3MTQxNjU4NTc3.ro0DLzCnyS8K598JU0Jh4cyuvm9nfquvxbCxfq225_gg.qYD8o8IOMBds5fLyl9tTP1I10O0-1pD916XN2yR2Dpsg.JPEG.gnssudpdy%2F1647141380147.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTA4MTVfMjEg%2FMDAxNTY1ODc0MTQ3NDc5.MiyJgZRDbtecLkGw3Dh85NkkARuuX2OfrkyEJqmeteUg.-6ohkYxh7RDFq8s1dpBxCOhDBAJLafro8v8MJRyiWN0g.JPEG%2FexternalFile.jpg&type=sc960_832",
    "https://cdn.pixabay.com/photo/2018/06/13/18/20/waves-3473335__480.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMJ2fSRUwbu1gpU8eS3wiyKsBCtphO8HpFow&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRljMbXgwicwW2cKPlcUTfWXewaPDAGb9ubw&usqp=CAU",
  ];

  return (
    <main className="h-max grid grid-cols-11">
      <article className="col-start-2 col-span-9">
        <div className="flex flex-wrap">
          {imageDatas &&
            imageDatas.map((url) => (
              <img
                lassName="flex m-4 w-16 h-16"
                src={url}
                alt=""
                style={{
                  maxHeight: "30vh",
                  minHeight: "30vh",
                  maxWidth: "30vh",
                  minWidth: "30vh",
                  margin: "30px",
                  marginLeft: "0px",
                  marginTop: "0px",
                  marginBottom: "30px",
                }}
              />
            ))}
        </div>
      </article>
    </main>
  );
};

export default ProfileFile;
