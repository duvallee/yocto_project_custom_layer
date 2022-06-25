

#include <functional>
#include <memory>

#include "rclcpp/rclcpp.hpp"
#include "std_msgs/msg/string.hpp"

using std::placeholders::_1;


class HelloworldSubscriber : public rclcpp::Node
{
public:
   HelloworldSubscriber()
   : Node("Helloworld_subscriber")
   {
      auto qos_profile = rclcpp::QoS(rclcpp::KeepLast(10));
      helloworld_subscriber_ = this->create_subscription<std_msgs::msg::Int32>("cubemx_publisher", qos_profile, std::bind(&HelloworldSubscriber::subscribe_topic_message, this, _1));
   }

private:
   void subscribe_topic_message(const std_msgs::msg::Int32::SharedPtr msg) const
   {
      RCLCPP_INFO(this->get_logger(), "Received message: '%d'", msg->data.Int32());
   }
   rclcpp::Subscription<std_msgs::msg::Int32>::SharedPtr helloworld_subscriber_;
};


int main(int argc, char * argv[])
{
   rclcpp::init(argc, argv);
   auto node = std::make_shared<HelloworldSubscriber>();
   rclcpp::spin(node);
   rclcpp::shutdown();
   return 0;
}


