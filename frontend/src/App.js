import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CollaboratorList from './components/AddCollaborator';
import BreakfastList from './components/CollaboratorList';
import AppNavbar from './components/AppNavbar';

function App() {
  return (
    <Router>
      <AppNavbar />
      <Routes>
        
        <Route exact path="/" element={<CollaboratorList />} />
        {/* <Route exact path="/collaborators" element={<CollaboratorList />} /> */}
        <Route exact path="/breakfasts" element={<BreakfastList />} />
      </Routes>
    </Router>
  );
}

export default App;
