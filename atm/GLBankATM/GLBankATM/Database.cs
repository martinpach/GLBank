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
                    connection.Close();
                }
            }
            return false;
        }

        public static bool isCardBlocked(long cardnumber)
        {
            string query = "SELECT blocked FROM bankcards WHERE cardnumber = " + cardnumber;
            MySqlConnection connection = getConnection();
            if(connection != null)
            {
                try
                {
                    MySqlCommand cmd = new MySqlCommand(query, connection);
                    connection.Open();
                    reader = cmd.ExecuteReader();                    
                    if (reader.Read() && reader.GetChar("blocked") == 'T')
                    {                        
                        return true;
                    }                      
                }
                catch(Exception ex) {
                    Console.WriteLine(ex.ToString());
                }
                finally
                {
                    reader.Close();
                    connection.Close();
                }
            }
            return false;
        }

        public static bool isPinCorrect(int pin, long cardnumber)
        {
            string query = "SELECT idcard FROM bankcards WHERE cardnumber = " + cardnumber + " AND pincode = " + pin;
            MySqlConnection connection = getConnection();
            if(connection != null)
            {
                try
                {
                    MySqlCommand cmd = new MySqlCommand(query, connection);
                    connection.Open();
                    reader = cmd.ExecuteReader();
                    if (reader.Read())
                    {
                        reader.Close();
                        connection.Close();
                        markPinAttempt(cardnumber, true);
                        return true;
                    }
                    reader.Close();
                    connection.Close();
                    markPinAttempt(cardnumber, false);
                }
                catch(Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                }
                finally
                {
                    reader.Close();
                    connection.Close();
                }
            }          
            return false;
        }

        private static void markPinAttempt(long cardnumber, bool correct)
        {
            string query;
            MySqlConnection connection = getConnection();
            if (!correct)
                query = "UPDATE bankcards SET incorrect_pincode_attempts = incorrect_pincode_attempts + 1 where cardnumber = " + cardnumber;
            else
            {                
                query = "UPDATE bankcards SET incorrect_pincode_attempts = 0 where cardnumber = " + cardnumber;
            }
            if(connection != null)
            {
                try
                {
                    MySqlCommand cmd = new MySqlCommand(query, connection);
                    connection.Open();
                    cmd.ExecuteNonQuery();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());                  
                }
                finally
                {
                    connection.Close();
                }
            }
        }

        public static void blockCard(long cardnumber)
        {
            string query = "UPDATE bankcards SET blocked = 'T' WHERE cardnumber = " + cardnumber;
            MySqlConnection connection = getConnection();
            if (connection != null)
            {
                try
                {
                    MySqlCommand cmd = new MySqlCommand(query, connection);
                    connection.Open();
                    cmd.ExecuteNonQuery();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                }
                finally
                {
                    connection.Close();
                }
            }
        }

        public static int getIncorrectPinAttempts(long cardnumber)
        {
            string query = "SELECT incorrect_pincode_attempts FROM bankcards WHERE cardnumber = " + cardnumber;
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
                        return reader.GetInt16("incorrect_pincode_attempts");
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                }
                finally
                {
                    reader.Close();
                    connection.Close();
                }
            }
            markPinAttempt(cardnumber, false);
            return 0;
        }
    }
}
