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
    <main className="h-screen flex justify-center items-center flex-col space-y-3">
<<<<<<< Updated upstream
      <article className="border border-gray-300  ">
=======
      <article className="border border-gray-300">
>>>>>>> Stashed changes
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
            {({ values, isSubmitting, isValid, handleChange, handleBlur }) => (
              <form>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="email"
                    placeholder="전화번호, 사용자 이름 또는 이메일"
                  />
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
                    <a href="/">로그인</a>
                  </button>
                </div>
<<<<<<< Updated upstream
                <div className="text-center font-normal text-sm  mb-2 mt-5">
                  또는
                </div>
=======
>>>>>>> Stashed changes
                <div className="text-center font-medium mb-2 mt-5 ">
                  <a href="#">Facebook으로 로그인</a>
                </div>
                <div className="text-center text-xs font- thin mb-6 mt-5">
                  <a href="#">비밀번호를 잊으셨나요?</a>
                </div>
              </form>
            )}
          </Formik>
        </div>
      </article>

      <article className="border border-gray-300">
        <div className="w-96 m-auto bg-white">
          <div className="text-center font-medium mb-5 mt-5 ">
            계정이 없으신가요?
            <a className="text-blue-500" href="/signup">
              가입하기
            </a>
          </div>
        </div>
      </article>
    </main>
  );
}

export default LoginPage;
