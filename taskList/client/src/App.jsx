import { Home } from './components/Home';
import { Routes, Route } from 'react-router-dom';
import { UserTasks } from './views/UserTasks';
import { Dashboard } from './views/Dashboard';
import { CreateUser } from './views/CreateUser';

function App() {

  return (
    <>
      <Routes>
        <Route element={<Home />}>
          <Route path='/' element={<Dashboard />} />
          <Route path='/user/:id' element={<UserTasks />} />
          <Route path='/add' element={<CreateUser />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
