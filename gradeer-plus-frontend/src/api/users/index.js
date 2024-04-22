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
    return await axios.post(`http://localhost:8080/users/add?name=${name}&role=${role}`).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}

export const deleteUser = async (id) => {
  try {
    return await axios.post(`http://localhost:8080/users/delete?id=${id}`).then(response => response)
  } catch (error) {
    throw {
      code: error.code,
      message: error.message,
      responseStatus: error.response?.status,
    }
  }
}
