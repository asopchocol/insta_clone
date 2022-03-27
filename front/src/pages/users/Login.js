import { Formik } from "formik";
import React, { useState } from "react";
import * as Yup from "yup";

function LoginPage() {
  const [passwordType, setPasswordType] = useState("password");
  const loginSchema = Yup.object().shape({
    email: Yup.string().required("필수 입력사항입니다."),
    password: Yup.string().required("필수 입력사항입니다."),
  });

  return (
    <main className="h-screen flex justify-center items-center">
      <article className="border border-gray-300">
        <div className="w-96 m-auto bg-white">
          <h1 className="text-center my-3">Instagram</h1>
          <Formik
            initialValues={{ email: "", password: "" }}
            validationSchema={loginSchema}
            onSubmit={(values, { setSubmitting }) => {
              setTimeout(() => {
                alert(JSON.stringify(values, null, 2));
                setSubmitting(false);
              }, 400);
            }}
            enableReinitialize
            validateOnMount
          >
            {({ isSubmitting, isValid, handleChange, handleBlur }) => (
              <form>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="email"
                    placeholder="전화번호, 사용자 이름 또는 이메일"
                  />{" "}
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type={passwordType}
                    placeholder="비밀번호"
                  />
                </div>
                <div className="mx-5 mb-2 mt-5">
                  <button
                    disabled={!isValid || isSubmitting}
                    className="bg-sky-300 text-white w-full py-1"
                  >
                    로그인
                  </button>
                </div>
              </form>
            )}
          </Formik>
        </div>
      </article>
    </main>
  );
}

export default LoginPage;
