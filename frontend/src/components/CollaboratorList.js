import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CollaboratorList = () => {
  const [collaborators, setCollaborators] = useState([]);
  const [editingCollaboratorId, setEditingCollaboratorId] = useState(null);
  const [editedName, setEditedName] = useState('');
  const [editedCPF, setEditedCPF] = useState('');
  const [editedBreakfastOption, setEditedBreakfastOption] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/collaborators')
      .then(response => {
        setCollaborators(response.data);
      })
      .catch(error => {
        console.error('Error fetching collaborators:', error);
      });
  }, []);

  const deleteCollaborator = (id) => {
    axios.delete(`http://localhost:8080/api/collaborators/${id}`)
      .then(() => {
        setCollaborators(collaborators.filter(collaborator => collaborator.id !== id));
      })
      .catch(error => {
        console.error('Error deleting collaborator:', error);
      });
  };

  const handleEdit = (id, name, cpf, breakfastOption) => {
    setEditingCollaboratorId(id);
    setEditedName(name);
    setEditedCPF(cpf);
    setEditedBreakfastOption(breakfastOption);
  };

  const handleSave = () => {
    const updatedCollaborator = {
      name: editedName,
      cpf: editedCPF,
      breakfastOption: editedBreakfastOption
    };

    axios.put(`http://localhost:8080/api/collaborators/${editingCollaboratorId}`, updatedCollaborator)
      .then(response => {
        const updatedCollaborators = collaborators.map(collaborator => {
          if (collaborator.id === editingCollaboratorId) {
            return {
              ...collaborator,
              name: editedName,
              cpf: editedCPF,
              breakfastOption: editedBreakfastOption
            };
          }
          return collaborator;
        });
        setCollaborators(updatedCollaborators);
        setEditingCollaboratorId(null);
        setEditedName('');
        setEditedCPF('');
        setEditedBreakfastOption('');
      })
      .catch(error => {
        console.error('Error updating collaborator:', error);
      });
  };

  return (
    <div>
      <h2>Lista de Colaboradores</h2>
      <ul>
        {collaborators.map(collaborator => (
          <li key={collaborator.id}>
            {editingCollaboratorId === collaborator.id ? (
              <div>
                <input
                  type="text"
                  value={editedName}
                  onChange={(e) => setEditedName(e.target.value)}
                />
                <input
                  type="text"
                  value={editedCPF}
                  onChange={(e) => setEditedCPF(e.target.value)}
                />
                <input
                  type="text"
                  value={editedBreakfastOption}
                  onChange={(e) => setEditedBreakfastOption(e.target.value)}
                />
                <button onClick={handleSave}>Save</button>
              </div>
            ) : (
              <div>
                Name: {collaborator.name} - CPF: {collaborator.cpf} - Breakfast: {collaborator.breakfastOption}
                <button onClick={() => deleteCollaborator(collaborator.id)}>Delete</button>
                <button onClick={() => handleEdit(collaborator.id, collaborator.name, collaborator.cpf, collaborator.breakfastOption)}>Edit</button>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CollaboratorList;
