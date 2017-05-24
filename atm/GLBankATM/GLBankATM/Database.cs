using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GLBankATM
{
    class Database
    {
        private const string SERVER = "localhost";
        private const string UID = "root";
        private const string PASSWORD = "";
        private const string DATABASE = "GLBank";
        private static MySqlConnection connection;
        static MySqlDataReader reader;
        private static MySqlConnection getConnection()
        {
            if (connection == null)
                connection = OpenConnection();
            return connection;
        }

        private Database()
        {            
        }


        private static MySqlConnection OpenConnection()
        {
            string connectionString = "SERVER=" + SERVER + ";";
            connectionString += "DATABASE=" + DATABASE + ";";
            connectionString += "UID=" + UID + ";";
            connectionString += "PASSWORD=" + PASSWORD + ";";
            MySqlConnection connection = new MySqlConnection(connectionString);
            return connection;
        }

        public static bool existCard(long cardnumber)
        {
            string query = "SELECT idcard FROM bankcards WHERE cardnumber = " + cardnumber;
            MySqlConnection connection = getConnection();
            if (connection != null)
            {
                try
                {
                    MySqlCommand cmd = new MySqlCommand(query, connection);
                    connection.Open();
                    reader = cmd.ExecuteReader();
                    if (reader.Read())
                    {
                        return true;
                    }
                }
                catch(Exception ex)
                {

                }
                finally
                {
                    reader.Close();
                }
            }
            return false;
        }
    }
}
