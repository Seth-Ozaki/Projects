import { Home } from './components/Home';
import { Routes, Route } from 'react-router-dom';
import { UserTasks } from './views/UserTasks';
import { Dashboard } from './views/Dashboard';

function App() {

  return (
    <>
      <Home />
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route path='/user/:id' element={<UserTasks />} />
      </Routes>
    </>
  );
}

export default App;
