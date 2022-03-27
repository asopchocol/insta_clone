import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import routes from '../common/routes/index';

function Router() {
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Routes>
          {routes.map((route, index) => (
            <Route key={index} path={route.path} element={<route.components />} />
          ))}
        </Routes>
      </BrowserRouter>
    </RecoilRoot>
  );
}
export default Router;
