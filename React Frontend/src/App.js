import React, { useState, useEffect } from 'react';
import { getFoodOrders, createFoodOrder, updateFoodOrder, deleteFoodOrder } from './api/FoodOrderService.js';

const App = () => {
  const [foodOrders, setFoodOrders] = useState([]);
  const [editFoodOrder, setEditFoodOrder] = useState(null);
  const [newFoodOrder, setNewFoodOrder] = useState({ meal: '', beverage: '' });
  
  // Hole alle Benutzer beim Initialisieren
  useEffect(() => {
    fetchFoodOrders();
  }, []);

  // Funktion zum Abrufen aller Bestellungen
  const fetchFoodOrders = async () => {
      const response = await getFoodOrders();
      setFoodOrders(response.data);
    };

  // Funktion zum Hinzufügen einer neuen Bestellung
  const handleCreateFoodOrders = async () => {
    if (newFoodOrder.meal && newFoodOrder.beverage) {
      await createFoodOrder(newFoodOrder);
      setNewFoodOrder({ meal:"", beverage: ""});
      fetchFoodOrders();
    }
  };

  // Funktion zum Aktualisieren eines Benutzers
  const handleUpdateFoodOrder = (foodOrder) => {
    setEditFoodOrder(foodOrder);
  }

  const saveUpdatedFoodOrder = async () => {
    if (editFoodOrder.meal && editFoodOrder.beverage) {
      await updateFoodOrder(editFoodOrder.id, { meal: editFoodOrder.meal, beverage: editFoodOrder.beverage});
      setEditFoodOrder(null);
      fetchFoodOrders();
    }
  }

  // Funktion zum Löschen eines Benutzers
  const handleDeleteFoodOrder = async (id) => {
    await deleteFoodOrder(id);
    fetchFoodOrders();
  };

  return (
    <div>
      <h1>Bestellverwaltung</h1>

      <div>
      <h2>Neue Bestellung hinzufügen</h2>
      <input
        type="text"
        placeholder="Speise"
        value={newFoodOrder.meal}
        onChange={(e) => setNewFoodOrder({ ...newFoodOrder, meal: e.target.value })}
      />
      <input
        type="text"
        placeholder="Getränk"
        value={newFoodOrder.beverage}
        onChange={(e) => setNewFoodOrder({ ...newFoodOrder, beverage: e.target.value })}
      />
      <button onClick={handleCreateFoodOrders}>Hinzufügen</button>
    </div>
    
    <ul>
      {foodOrders.map((foodOrder) => (
        <li key={foodOrder.id}>
          {editFoodOrder?.id === foodOrder.id ? (
            <div>
              <input
                type="text"
                value={editFoodOrder.meal}
                onChange={(e) => setEditFoodOrder({...editFoodOrder, meal: e.target.value})}
                placeholder="Speise ändern"
              />
                <input
                  type="text"
                  value={editFoodOrder.beverage}
                  onChange={(e) => setEditFoodOrder({...editFoodOrder, beverage: e.target.value})}
                  placeholder="Getränk ändern"
                />
                <button onClick={saveUpdatedFoodOrder}>Speichern</button>
                <button onClick={() => setEditFoodOrder(null)}>Schließen</button>
              </div>
          ) : (
            <div>
              <p>Speise: {foodOrder.meal}, Getränk: {foodOrder.beverage}</p>
              <button onClick={() => handleUpdateFoodOrder(foodOrder)}>Bearbeiten</button>
              <button onClick={() => handleDeleteFoodOrder(foodOrder.id)}>Löschen</button>
            </div>
          )
        }
        </li>
      ))}
    </ul>
  </div>
  );
}

export default App;
