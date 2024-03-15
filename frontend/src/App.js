
import { Route, Routes } from 'react-router-dom';
import './App.css';
import CustomerRouter from './Routers/CustomerRouter';

function App() {
  return (
    <div className="">
      <Routes>
        <Route path='/*' element={<CustomerRouter />}></Route>

      </Routes>
    </div>
  );
}

export default App;
