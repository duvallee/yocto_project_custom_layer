
#include <chrono>
#include <functional>
#include <memory>
#include <string>

#include "rclcpp/rclcpp.hpp"
#include "std_msgs/msg/string.hpp"
#include "std_msgs/msg/int32.hpp"

using namespace std::chrono_literals;


class HelloworldPublisher : public rclcpp::Node
{
public:
   HelloworldPublisher()
   : Node("helloworld_publisher"), count_(0)
   {
      auto qos_profile = rclcpp::QoS(rclcpp::KeepLast(10));
      helloworld_publisher_ = this->create_publisher<std_msgs::msg::Int32>("cubemx_publisher", qos_profile);
      timer_ = this->create_wall_timer(1s, std::bind(&HelloworldPublisher::publish_helloworld_msg, this));
   }

private:
   void publish_helloworld_msg()
   {
      auto msg = std_msgs::msg::Int32();
      msg.data = count_++;
      RCLCPP_INFO(this->get_logger(), "Published message: '%d'", msg.data);
      helloworld_publisher_->publish(msg);
   }
   rclcpp::TimerBase::SharedPtr timer_;
   rclcpp::Publisher<std_msgs::msg::Int32>::SharedPtr helloworld_publisher_;
   size_t count_;
};


int main(int argc, char * argv[])
{
   rclcpp::init(argc, argv);
   auto node = std::make_shared<HelloworldPublisher>();
   rclcpp::spin(node);
   rclcpp::shutdown();
   return 0;
}


