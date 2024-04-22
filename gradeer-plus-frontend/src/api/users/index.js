import axios from 'axios'

export const getAllUsers = async () => {
  try {
    return await axios.get('http://localhost:8080/users').then(response => response.data)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const addUser = async (name, role) => {
  try {
    const params = {
      name: name,
      role: role
    }
    return await axios.post(`http://localhost:8080/users/add`, { params }).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}
