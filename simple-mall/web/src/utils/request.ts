import { AXIOS } from "./http/axios";

// GET
export function Get<T>(url: string, params?: any): Promise<T> {
  return AXIOS.get<T>(url, { params })
    .then((response) => response.data)
    .catch((error) => {
      throw error;
    });
}

// Post
export function Post<T>(url: string, data?: any): Promise<T> {
    return AXIOS.post<T>(url, data)
      .then((response) => response.data)
      .catch((error) => {
        throw error;
      });
  }
  

// Put
export function Put<T>(url: string, params?: any): Promise<T> {
    return AXIOS.put<T>(url, { params })
      .then((response) => response.data)
      .catch((error) => {
        throw error;
      });
  }

// Delete
export function Delete<T>(url: string, params?: any): Promise<T> {
  return AXIOS.delete<T>(url, { params })
    .then((response) => response.data)
    .catch((error) => {
      throw error;
    });
}
