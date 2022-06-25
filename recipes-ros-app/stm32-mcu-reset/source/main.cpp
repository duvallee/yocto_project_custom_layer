// ****************************************************************
//
// File: main.cpp
//
// Written by duvallee in 2022
//
// ****************************************************************
#include "stdio.h"
#include <unistd.h>
#include "libmipi_api.h"

// ----------------------------------------------------------------
libmipi_api_camera_instance* g_pMipiCameraInterface = NULL;


int main(int argc, char* argv[])
{
   libmipi_api_camera_gpio_alt_fn alt_fn = MMAL_GPIO_FN_INPUT;
   libmipi_api_camera_gpio_pull pull = MMAL_GPIO_PULL_NO;
   libmipi_api_camera_gpio_level level = MMAL_GPIO_LEVEL_LOW;

   // --------------------------------------------------------------
   g_pMipiCameraInterface = libmipi_api_camera_init();
   if (g_pMipiCameraInterface == NULL)
   {
      printf("failed libmipi_api_camera_init() \r\n");
      return -1;
   }

   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_8, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }


   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_9, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }


   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_16, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }

   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_17, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }

   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_18, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }

   if (libmipi_api_get_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_19, &alt_fn, &pull, &level) < 0)
   {
      printf("failed libmipi_api_get_config_gpio() \r\n");
      goto error_condition;
   }

   // --------------------------------------------------------------
   // TXD4
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_8, MMAL_GPIO_FN_ALT_4, MMAL_GPIO_PULL_NO, MMAL_GPIO_LEVEL_HIGH) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // RXD4
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_9, MMAL_GPIO_FN_ALT_4, MMAL_GPIO_PULL_NO, MMAL_GPIO_LEVEL_HIGH) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // STM32_RESET
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_16, MMAL_GPIO_FN_OUTPUT, MMAL_GPIO_PULL_UP, MMAL_GPIO_LEVEL_HIGH) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // STM_TO_RASPBERRY_PI
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_17, MMAL_GPIO_FN_INPUT, MMAL_GPIO_PULL_NO, MMAL_GPIO_LEVEL_LOW) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // RASPBERRY_PI_TO_STM
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_18, MMAL_GPIO_FN_OUTPUT, MMAL_GPIO_PULL_UP, MMAL_GPIO_LEVEL_LOW) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // EXT_SIGNAL (from MCU alive)
   if (libmipi_api_set_config_gpio(g_pMipiCameraInterface, MMAL_GPIO_19, MMAL_GPIO_FN_INPUT, MMAL_GPIO_PULL_NO, MMAL_GPIO_LEVEL_LOW) < 0)
   {
      printf("failed libmipi_api_set_config_gpio() \r\n");
      goto error_condition;
   }

   // --------------------------------------------------------------
   // MCU Reset (STM32_RESET, MMAL_GPIO_16)
   if (libmipi_api_set_gpio_value(g_pMipiCameraInterface, MMAL_GPIO_16, MMAL_GPIO_LEVEL_LOW) < 0)
   {
      printf("failed libmipi_api_set_gpio_value() \r\n");
      goto error_condition;
   }

   usleep(100000);

   if (libmipi_api_set_gpio_value(g_pMipiCameraInterface, MMAL_GPIO_16, MMAL_GPIO_LEVEL_HIGH) < 0)
   {
      printf("failed libmipi_api_set_gpio_value() \r\n");
      goto error_condition;
   }

   // --------------------------------------------------------------
error_condition:
   libmipi_api_camera_deinit(g_pMipiCameraInterface);

   return 0;
}







