import axios from "axios";

const API_URL = "http://localhost:8080/api/foodorders";

export const getFoodOrders = () => axios.get(API_URL);
export const createFoodOrder = (foodOrder) => axios.post(API_URL, foodOrder);
export const updateFoodOrder = (id, foodOrder) => axios.put(`${API_URL}/${id}`, foodOrder);
export const deleteFoodOrder = (id) => axios.delete(`${API_URL}/${id}`);